# Country List Android Application

This application is built as an assignment to showcase my experience in Android Developement using Kotlin. App fetch a list of countries from (https://gist.githubusercontent.com/peymano-
wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json) to list the countries in a RecyclerView.

<img src="https://github.com/user-attachments/assets/372a1b9d-0507-4438-8d46-f341771b0af8" alt="CountryList App" width="200"/>

### How to Run the Application on Your Machine

Clone the Repository:
1. Use the following command to clone the project repository to your local machine:
     `git clone git@github.com:vimosanandev/country-list-app.git`

2. Switch to the Main Branch:
    After cloning, navigate to the project directory and ensure you are on the default main branch:
       `git checkout main`
   
3. Resolve Gradle Issues:
    Open the project in Android Studio. If any Gradle-related issues occur, resolve them by syncing the project with Gradle files.
      - Navigate to File > Sync Project with Gradle Files if prompted.
      - Ensure all required dependencies are installed.
4. Run the Application:
    Once all issues are resolved, click on the Run icon (a green play button) in Android Studio to build and launch the application.


### Technologies Used
- Kotlin (v2.1.0) Programming Language
- MVVM Architecture + Clean Architecture (Model-View-ViewModel Architecture)
- Kotlin Coroutines for thread handling of Asynchronous task
- Room SQLite Abstraction library for offline support
- Retrofit for Network Calls

### Extras
- No Dependecy Injection framework such as Dagger 2, Hilt were not used as mentioned in the assignment. Manual DI approach is used.
- Data Binding is enabled to bind data to the view.
- Supports min SDK of 24 ( > APK 24)
- KAPT (Kotlin Annotation Processing Tool) is used for data binding and ROOM. KSP (Kotlin Symbol Processing) is not used as Data Binding is not supported with KSP, even ROOM supports KSP.

Copyright (C) [2024-17-12] [Vimosanan]


