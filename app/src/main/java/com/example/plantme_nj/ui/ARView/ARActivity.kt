package com.example.plantme_nj.ui.ARView

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.plantme_nj.R
import com.example.plantme_nj.helpers.*
import com.example.plantme_nj.rendering_KT.ObjectRenderer
import com.example.plantme_nj.rendering_KT.BackgroundRenderer
import com.example.plantme_nj.rendering_KT.PlaneRenderer
import com.example.plantme_nj.rendering_KT.PointCloudRenderer
import com.example.plantme_nj.rendering_KT.PlaneAttachment
import com.example.plantme_nj.rendering_KT.Mode
import com.google.ar.core.*
import com.google.ar.core.exceptions.*
import java.io.IOException
import java.util.concurrent.ArrayBlockingQueue
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class ARActivity : AppCompatActivity(), GLSurfaceView.Renderer {
    private val TAG: String = ARActivity::class.java.simpleName

    private var installRequested = false

    private var mode: Mode = Mode.VIKING

    private var session: Session? = null

    // Tap handling and UI.
    private lateinit var gestureDetector: GestureDetector
    private lateinit var trackingStateHelper: TrackingStateHelper
    private lateinit var displayRotationHelper: DisplayRotationHelper
    private val messageSnackbarHelper: SnackbarHelper = SnackbarHelper()

    private val backgroundRenderer: BackgroundRenderer = BackgroundRenderer()
    private val planeRenderer: PlaneRenderer = PlaneRenderer()
    private val pointCloudRenderer: PointCloudRenderer = PointCloudRenderer()

    private val plantObject = ObjectRenderer()
    private var plantAttachment: PlaneAttachment? = null

    //FROM TUTORIAL
    private val vikingObject = ObjectRenderer()
    private val cannonObject = ObjectRenderer()
    private val targetObject = ObjectRenderer()

    private var vikingAttachment: PlaneAttachment? = null
    private var cannonAttachment: PlaneAttachment? = null
    private var targetAttachment: PlaneAttachment? = null
    //END TUTORIAL

    // Temporary matrix allocated here to reduce number of allocations and taps for each frame.
    private val maxAllocationSize = 16
    private val anchorMatrix = FloatArray(maxAllocationSize)
    private val queuedSingleTaps = ArrayBlockingQueue<MotionEvent>(maxAllocationSize)

    //Issue here??
    private lateinit var gLView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gLView = GLSurfaceView(this)
        setContentView(gLView)
//        setContentView(R.layout.activity_aractivity)

        trackingStateHelper = TrackingStateHelper(this@ARActivity)
        displayRotationHelper = DisplayRotationHelper(this@ARActivity)

        installRequested = false

        setupTapDetector()
        setupSurfaceView()
    }

    fun onRadioButtonClicked(view: View) {
        when (view.id) {
            R.id.radioCannon -> mode = Mode.CANNON
            R.id.radioTarget -> mode = Mode.TARGET
            else -> mode = Mode.VIKING
        }
    }

    private fun setupSurfaceView() {
        // Set up renderer.
        gLView.preserveEGLContextOnPause = true
        gLView.setEGLContextClientVersion(2)
        gLView.setEGLConfigChooser(8, 8, 8, 8, maxAllocationSize, 0)
        gLView.setRenderer(this)
        gLView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        gLView.setWillNotDraw(false)
        gLView.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
    }

    private fun setupTapDetector() {
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                onSingleTap(e)
                return true
            }

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }
        })
    }

    private fun onSingleTap(e: MotionEvent) {
        // Queue tap if there is space. Tap is lost if queue is full.
        queuedSingleTaps.offer(e)
    }

    override fun onResume() {
        super.onResume()

        if (session == null) {
            if (!setupSession()) {
                return
            }
        }

        try {
            session?.resume()
        } catch (e: CameraNotAvailableException) {
            messageSnackbarHelper.showError(this@ARActivity, getString(R.string.camera_not_available))
            session = null
            return
        }

        gLView.onResume()
        displayRotationHelper.onResume()
    }

    private fun setupSession(): Boolean {
        var exception: Exception? = null
        var message: String? = null

        try {
            when (ArCoreApk.getInstance().requestInstall(this@ARActivity, !installRequested)) {
                ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                    installRequested = true
                    return false
                }
                ArCoreApk.InstallStatus.INSTALLED -> {
                }
                else -> {
                    message = getString(R.string.arcore_install_failed)
                }
            }

            // Requesting Camera Permission
            if (!CameraPermissionHelper.hasCameraPermission(this@ARActivity)) {
                CameraPermissionHelper.requestCameraPermission(this@ARActivity)
                return false
            }

            // Create the session.
            session = Session(this@ARActivity)

        } catch (e: UnavailableArcoreNotInstalledException) {
            message = getString(R.string.please_install_arcore)
            exception = e
        } catch (e: UnavailableUserDeclinedInstallationException) {
            message = getString(R.string.please_install_arcore)
            exception = e
        } catch (e: UnavailableApkTooOldException) {
            message = getString(R.string.please_update_arcore)
            exception = e
        } catch (e: UnavailableSdkTooOldException) {
            message = getString(R.string.please_update_app)
            exception = e
        } catch (e: UnavailableDeviceNotCompatibleException) {
            message = getString(R.string.arcore_not_supported)
            exception = e
        } catch (e: Exception) {
            message = getString(R.string.failed_to_create_session)
            exception = e
        }

        if (message != null) {
            messageSnackbarHelper.showError(this@ARActivity, message)
            Log.e(TAG, getString(R.string.failed_to_create_session), exception)
            return false
        }

        return true
    }

    override fun onPause() {
        super.onPause()

        if (session != null) {
            displayRotationHelper.onPause()
            gLView.onPause()
            session!!.pause()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        results: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, results)
        if (!CameraPermissionHelper.hasCameraPermission(this@ARActivity)) {
            Toast.makeText(
                this@ARActivity,
                getString(R.string.camera_permission_needed),
                Toast.LENGTH_LONG
            ).show()

            // Permission denied with checking "Do not ask again".
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this@ARActivity)) {
                CameraPermissionHelper.launchPermissionSettings(this@ARActivity)
            }
            finish()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        FullScreenHelper.setFullScreenOnWindowFocusChanged(this@ARActivity, hasFocus)
    }


    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f)

        // Prepare the rendering objects. This involves reading shaders, so may throw an IOException.
        try {
            // Create the texture and pass it to ARCore session to be filled during update().
            backgroundRenderer.createOnGlThread(this@ARActivity)
            planeRenderer.createOnGlThread(this@ARActivity, getString(R.string.model_grid_png))
            pointCloudRenderer.createOnGlThread(this@ARActivity)

            // TODO - set up the objects
        } catch (e: IOException) {
            Log.e(TAG, getString(R.string.failed_to_read_asset), e)
        }
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        displayRotationHelper.onSurfaceChanged(width, height)
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        // Clear screen to notify driver it should not load any pixels from previous frame.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

        session?.let {
            // Notify ARCore session that the view size changed
            displayRotationHelper.updateSessionIfNeeded(it)

            try {
                it.setCameraTextureName(backgroundRenderer.textureId)

                val frame = it.update()
                val camera = frame.camera

                // Handle one tap per frame.
                handleTap(frame, camera)
                drawBackground(frame)

                // Keeps the screen unlocked while tracking, but allow it to lock when tracking stops.
                trackingStateHelper.updateKeepScreenOnFlag(camera.trackingState)

                // If not tracking, don't draw 3D objects, show tracking failure reason instead.
                if (!isInTrackingState(camera)) return

                val projectionMatrix = computeProjectionMatrix(camera)
                val viewMatrix = computeViewMatrix(camera)
                val lightIntensity = computeLightIntensity(frame)

                visualizeTrackedPoints(frame, projectionMatrix, viewMatrix)
                checkPlaneDetected()
                visualizePlanes(camera, projectionMatrix)

                // TODO: Call drawObject() for Viking, Cannon and Target here
                drawObject(
                    vikingObject,
                    vikingAttachment,
                    Mode.VIKING.scaleFactor,
                    projectionMatrix,
                    viewMatrix,
                    lightIntensity
                )

                drawObject(
                    cannonObject,
                    cannonAttachment,
                    Mode.CANNON.scaleFactor,
                    projectionMatrix,
                    viewMatrix,
                    lightIntensity
                )

                drawObject(
                    targetObject,
                    targetAttachment,
                    Mode.TARGET.scaleFactor,
                    projectionMatrix,
                    viewMatrix,
                    lightIntensity
                )

            } catch (t: Throwable) {
                Log.e(TAG, getString(R.string.exception_on_opengl), t)
            }
        }
    }

    private fun isInTrackingState(camera: Camera): Boolean {
        if (camera.trackingState == TrackingState.PAUSED) {
            messageSnackbarHelper.showMessage(
                this@ARActivity, TrackingStateHelper.getTrackingFailureReasonString(camera)
            )
            return false
        }
        else {
            return true
        }
    }

    private fun drawObject(
        objectRenderer: ObjectRenderer,
        planeAttachment: PlaneAttachment?,
        scaleFactor: Float,
        projectionMatrix: FloatArray,
        viewMatrix: FloatArray,
        lightIntensity: FloatArray
    ) {
        if (planeAttachment?.isTracking == true) {
            planeAttachment.pose.toMatrix(anchorMatrix, 0)

            // Update and draw the model
            objectRenderer.updateModelMatrix(anchorMatrix, scaleFactor)
            objectRenderer.draw(viewMatrix, projectionMatrix, lightIntensity)
        }
    }

    private fun drawBackground(frame: Frame) {
        backgroundRenderer.draw(frame)
    }

    private fun computeProjectionMatrix(camera: Camera): FloatArray {
        val projectionMatrix = FloatArray(maxAllocationSize)
        camera.getProjectionMatrix(projectionMatrix, 0, 0.1f, 100.0f)

        return projectionMatrix
    }

    private fun computeViewMatrix(camera: Camera): FloatArray {
        val viewMatrix = FloatArray(maxAllocationSize)
        camera.getViewMatrix(viewMatrix, 0)

        return viewMatrix
    }

    /**
     * Compute lighting from average intensity of the image.
     */
    private fun computeLightIntensity(frame: Frame): FloatArray {
        val lightIntensity = FloatArray(4)
        frame.lightEstimate.getColorCorrection(lightIntensity, 0)

        return lightIntensity
    }

    /**
     * Visualizes tracked points.
     */
    private fun visualizeTrackedPoints(
        frame: Frame,
        projectionMatrix: FloatArray,
        viewMatrix: FloatArray
    ) {
        // Use try-with-resources to automatically release the point cloud.
        frame.acquirePointCloud().use { pointCloud ->
            pointCloudRenderer.update(pointCloud)
            pointCloudRenderer.draw(viewMatrix, projectionMatrix)
        }
    }

    /**
     *  Visualizes planes.
     */
    private fun visualizePlanes(camera: Camera, projectionMatrix: FloatArray) {
        planeRenderer.drawPlanes(
            session!!.getAllTrackables(Plane::class.java),
            camera.displayOrientedPose,
            projectionMatrix
        )
    }

    /**
     * Checks if any tracking plane exists then, hide the message UI, otherwise show searchingPlane message.
     */
    private fun checkPlaneDetected() {
        if (hasTrackingPlane()) {
            messageSnackbarHelper.hide(this@ARActivity)
        } else {
            messageSnackbarHelper.showMessage(
                this@ARActivity,
                getString(R.string.searching_for_surfaces)
            )
        }
    }

    /**
     * Checks if we detected at least one plane.
     */
    private fun hasTrackingPlane(): Boolean {
        val allPlanes = session!!.getAllTrackables(Plane::class.java)

        for (plane in allPlanes) {
            if (plane.trackingState == TrackingState.TRACKING) {
                return true
            }
        }

        return false
    }

    fun handleTap(frame: Frame, camera: Camera)
    {
        val tap = queuedSingleTaps.poll()

        if (tap != null && camera.trackingState == TrackingState.TRACKING) {
            // Check if any plane was hit, and if it was hit inside the plane polygon
            for (hit in frame.hitTest(tap)) {
                val trackable = hit.trackable

                if ((trackable is Plane
                            && trackable.isPoseInPolygon(hit.hitPose)
                            && PlaneRenderer.calculateDistanceToPlane(hit.hitPose, camera.pose) > 0)
                    || (trackable is Point
                            && trackable.orientationMode
                            == Point.OrientationMode.ESTIMATED_SURFACE_NORMAL)
                ) {
                    when (mode) {
                        Mode.VIKING -> vikingAttachment = addSessionAnchorFromAttachment(vikingAttachment!!, hit)
                        Mode.CANNON -> cannonAttachment = addSessionAnchorFromAttachment(cannonAttachment!!, hit)
                        Mode.TARGET -> targetAttachment = addSessionAnchorFromAttachment(targetAttachment!!, hit)
                    }
                    // TODO: Create an anchor if a plane or an oriented point was hit
                    break
                }
            }
        }
    }

    // TODO: Add addSessionAnchorFromAttachment() function here
    private fun addSessionAnchorFromAttachment(previousAttachment: PlaneAttachment, hit: HitResult?): PlaneAttachment
    {
        // 1
        previousAttachment.anchor.detach()

        // 2
        val plane = hit?.trackable as Plane
        val anchor = session!!.createAnchor(hit.hitPose)

        // 3
        return PlaneAttachment(plane, anchor)
    }
}