# :potted_plant: PlantMe-NJ :potted_plant:

# Table of Contents
- [Web Page](https://www.immseniorshow.com/amy-vargas)
- [Pitch](#pitch)
- [Inspiration](#inspiration)
- [Premise](#premise)
- [Features](#features)
- [Future Hopes](#future-features)
- [Sample Photos](photos/design-plants.md)
- [Design Plans](photos/design-plants.md)
- [Previous Mock-Up](https://github.com/A-Vargas-GP/Plant4U-MicroThesis)
- [Proposal](docs/Final_Proposal-Amy_Vargas.pdf)
- [Inspiration](#inspiration)
- [Installation](#installation)
- [Language and Frameworks](#language-and-frameworks)
- [Key Terms](#key-terms)
- [Credits](#credits)

## Pitch
Tired of buying plants and letting them die because you just don’t know how to take care of them? Well, look no further! PlantMe-NJ can help your plants survive by showing you essential gardening information like planting distance and ideal planting months. It can even show you how your plants will look like using AR, and it can suggest an ideal plant based on your preferences and environment conditions. 

Check out the [PlantMe-NJ website](https://www.immseniorshow.com/amy-vargas) if you are interested in learning more!

## Premise
PlantMe-NJ is an Android app dedicated to the aspect of gardening, specifically focusing on New Jersey crops and necessary gardening information, to promote a successful planting season every spring and summer.

The program indicates what plant is ideal for you based on your environment and personal choices, and it will have a feature that can display a potential plant in a selected spot using AR.

## Features
* Plant Information
    - Within the application, crop information will be readily available where a user can view:
        - List of plants best grown in NJ
        - Starting planting and harvesting dates
        - Description of each plant (i.e. spacing, depth, ideal environment)
* Weather
    - On the home page, using your current location, it will display the current temperature, weather conditions, humidity, and other pertinent information.
* Plant Selection
    - Within the application, you will be able to select on two types: plant type or season. Based on your selection, crops from the selected plant type will appear for users to scroll through.
* AR
    - Within the application, through the camera feature, you will be able to view a 3D-modeled version of a selected fully-grown crop. The idea is to combine the user’s garden area with plant models, so the user is able to visualize the size and potential of their intended plant.
* *"What's My Ideal Plant"* Quiz
   - Users will be able to take a quiz that asks them about pertinent gardening questions, interests, moods, etc.
   - Based on their response, users will be recommend the ideal plant that is meant for them!

## Future Features

In the current version, the user is able to view an AR model of a viking. However, the intended goal was to have a 3D model of the selected plant appear. Due to time constraints, this feature is not completely implemented. 
While attempting to complete this functionality, I encountered unexpected technical challenges that required additional time and resources to overcome. I am hopeful that in the future, as I continue development, the feature functions flawlessly and provides a seamless user experience.

## Inspiration
I aimed to develop a mobile application using my programming skills and design abilities. Using Android Studio, the app would be focused on gardening, specifically selecting which plants would be ideal to plant in New Jersey, determining which plant is suitable for a user, and visually displaying the potential plant in a specific spot using AR. It includes information regarding a specific crop, such as planting type, planting distance, harvesting time, planting time, and more. To form the design features, I used Adobe Photoshop, Illustrator, and XD.

This project is reflective of a hobby that I am passionate about and indicates my desire to create a project that could be useful in my life. About six years ago, I taught myself how to garden and I grew several types of vegetables like tomatoes, jalapenos, cilantro, and mint. Through meticulous research, I bought tools to culminate successful plants like indoor plant lights, pots, and a heating pad, and learned about the concept of germination, transplanting, and growth seasons. However, because of the lack of space, I, unfortunately, had to remove all of the plants and leave the hobby behind. Luckily, this year, with more space, I had the opportunity to rekindle my gardening. This spring, I set out to complete my previous goal of regaining my green thumb, and I managed to get the flower beds ready for planting. Despite having experience with having plants produce frequent crops, most of my plants did not get to the seedling stage.

After an unsuccessful harvest season, I found it complicated to figure out exactly what went wrong. Why did my strawberries never grow? Did I plant them in the wrong spot? Why did my watermelon plant take over the entire garden bed? Why did my watermelon never turn red inside? Despite researching on numerous websites, I could not conclude what might have been the possible reasons behind the faulty growth. Reasons ranged from a weak seed, unhealthy soil, lack of sun, not enough space, and more. It was unlikely to be unhealthy soil since I had just bought a new potting mix, and it was not lacking sun since it is facing the sun’s direction. However, one thing that I forgot to keep note of was the right planting time and enough plant distance. From brief research online, there were not many helpful tools to keep track of this information. For instance, there were only websites that gave the right watering day and possible planting formats to promote a good growing space. Thus, I thought this would be the perfect opportunity to turn this into a year-long project where I can turn my idea of an app that can map plant space and show an end-product of a plant into a functional product.

## Installation

### Downloading APK

First, for your Android Device, 
* Open the Settings app on your Android device.
* In the Settings menu, tap Apps.
* Tap Special app access (or Advanced > Special app access).
* Tap Install unknown apps.
* Select an app to use to install an APK file—your browser and file management apps are the best option here.
* Tap the Allow from this source slider to allow APK files to be installed via that app.

In the folder labeled 'RELEASE,' there will be a file named [`app-debug.apk`](RELEASE/app-debug.apk)

Once downloaded, locate the file on your phone in your Downloads folder, and tap the APK file.

If prompted, allow for install.

### Building the App Project

First, clone the repo: 

`git clone git@github.com:YourUserName/PlantMe-NJ_App.git`

Using Android Studio, 

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project (Eclipse ADT, Gradle, etc.)` and navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Running the App Project

Connect an Android device to your computer.

With Android Studio open, 
* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select your connected Android device and click 'OK'

## Key Terms
- Perennial Plant: plants that live for more than two years
- Annual Plant: plants that flower and die in one season
- Cool Season: Spring and fall
- Warm Season: Summer

## Language and Frameworks
Using Android Studio as the IDE, this app is coded in Kotlin and SQLite for the backend. It features an ARCoreSDK, Typeform, and a WeatherAPI integration. 

In the previous version, it used a WeatherBit API integration.


## Credits
- AR Related Resources
    - [AR Template](https://www.kodeco.com/6986535-arcore-with-kotlin-getting-started)
    - [AR Core SDK](https://developers.google.com/ar/develop/java/quickstart)
- XML Layout Resources
    - [Cardview](https://developer.android.com/develop/ui/views/layout/cardview)
    - [ScrollView](https://www.geeksforgeeks.org/scrollview-in-android/)
    - [Splash Screen](https://developer.android.com/develop/ui/views/launch/splash-screen)
    - [Fragment](https://medium.com/@martinbaraya/fragments-tutorial-with-example-in-android-studio-6f92f53ad8cd)
- SQLite Related Resources
    - [SQLite Tutorial](https://nrohpos.medium.com/implement-sqlite-in-android-kotlin-39bc42e97ab1)
    - [SQLite Setup](https://www.youtube.com/watch?v=9LYn-OBO5qE)
- Weather Related Resources
    - [WeatherBitAPI Setup](https://www.geeksforgeeks.org/how-to-build-a-weather-app-in-android/)
    - [WeatherBitAPI Data Meaning](https://www.weatherbit.io/api/weather-current)
    - [WeatherAPI Data Meaning](https://www.weatherapi.com/docs/#)