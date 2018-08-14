# Android_Lifecycle

### Requirements
Start with your Image Viewer app override methods for all the activity states so that the system logs each time a state is entered.

### Track the activity states
1. Duplicate your Image Viewer app.
2. Override all Activity Lifecycle Methods in each of your Activities.
3. For each of those, log when the Lifecycle Method is called.
4. In the log, be sure to include the name of the activity and the name of the state being entered.
> Use `this.getClass().getSimpleName()` to programatically get the name of the current activity being run

### Submit
Send your completed app to your Project Manager.

### Challenge
Move the functionality of your app around to match what should be done in each of the app states.
