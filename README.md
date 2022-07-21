Hello 👋

This project is a simple to-do application for task management. 
Key features of the app are:
1. Creating a task
2. Marking the task as complete or incomplete
3. Delete task from the list.

Technologies to develop this application
1. Dagger2
2. Kotlin flow
3. Room database
4. ButterKnife

The design pattern used to develop this app is MVVM (Model View ViewModel)
https://developer.android.com/topic/architecture

**How to use?**

See the quick video below:

![how_to_use](https://user-images.githubusercontent.com/8465561/180029292-460a68af-946a-4887-873f-f1afb8b08f0f.gif)

**Running the App project**

To run the project, you need to install android studio
https://developer.android.com/studio

After downloading and installing android studio. Open the project by selecting the Todo app root folder.

![Screenshot 2022-07-21 000622](https://user-images.githubusercontent.com/8465561/180030124-38866d46-41c2-4f6a-8887-fdb16b074109.png)

Wait until the project is fully loaded.

Run the project by selecting the emulator device or connecting an android phone to the PC/MAC.

Instructions to create an emulator in Android Studio: 
https://developer.android.com/studio/run/managing-avds

Instructions to enable device debugging and connecting to the android device:
https://developer.android.com/studio/run/device

After setting up the emulator or device, click the Run button.

![Screenshot 2022-07-21 000734](https://user-images.githubusercontent.com/8465561/180030847-ebf8e3df-7e01-4a26-b628-faac11ae6aca.png)

**Running Unit Test**

To run the unit test. Select any test options on app configuration near the build icon
The unit test focuses on the app's business logic and database (ViewModel and TodoDatabase).

![testing_options](https://user-images.githubusercontent.com/8465561/180031503-a581edc3-2de5-4f1a-a1c9-8d37cb4688e4.png)

After selecting the preferred unit test. 

Click Run


**NOTE**

The app has default stub data. To remove the stub data. Goto TodoDatabase.kt
app\src\main\java\com\todo\app\data\TodoDatabase.kt

**SHORT REFLECTION**

Developing the app is a learning experience for me. Most of the android projects I've been involved in were written in java. Kotlin is fairly new to me in android development, but I took it as a personal challenge to write the program for this to-do application entirely in kotlin. Doing so helped me to understand the language better and set me in a situation where I improved my skills.
