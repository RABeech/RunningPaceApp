Author: Adam Beech
Date: 12/19/2018

Description:
This application was built to be used in a static location such as a treadmill. It takes in time and distance parameters and returns the average pace per mile of the user. As an option, the application connects to a Google Firebase DB to record the user’s results. There is a view that is a custom built recycler that displayed previously saved user data.

Features:
This application was originally built in Android Studio 2.3. It features a custom application icon and splash screen. It implements a drawer for menu navigation and view listings. Views consist of home, about, settings, run calculation, saved, and running resources screens. The application uses material design elements such as the app drawer and floating action buttons. The app drawer has a custom gradient effect with font art. The application implements a customer floating action button animation built with XML classes on the results view that expands and collapses additional options on user input. The saved runs view is a custom built recycler view that displays dynamically saved user information. The information is saved to a Google Firebase DB. The application implements global intents for sharing to social media and user feedback to development.
Program logic for the calculations is found in app/src/res/’ java/com/rabeech/runningpacecalculator’/ResultActivity.java

This application was built as a side project to be used by the author for indoor running and is not in active development.