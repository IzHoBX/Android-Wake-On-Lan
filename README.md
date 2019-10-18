# Android-Wake-On-Lan (WOL) on Notification
Application that wakes up your pc from sleeping or hibernating

## Disclaimer
The WOL implementation, which is most part of this application, is forked from JediMaster93's repo, refer: https://github.com/JediMaster93/Android-Wake-On-Lan. 
My contribution involves less than 10 lines of code to link it up with notifications. 

## Background
There is an ordinary ambition among people like me who likes to "act" cool: turn on pc using through voice command at Google Home. While there are plenty of implementation out there that uses some sort of web request, the nature itself has these problems:
1. complicated (need to setup web APIs or some routing server)
2. compromises privacy , if you uses third party routing server (if you care about it)
3. lacks stability - that's how enthusiasts technology works! 
This app, solves overcome the shortcomings by using ifttt's server to send the web request into the ifttt app, which is more reliable (and since you are already using ifttt anyway). 

Android app Tasker is definitely another alternative, but if you are using it solely for WOL, 
1. the price is not justified, 
2. its notification listener service is not working properly on Andorid 5 (at least on my Xperia Z Ultra)
3. it's annoying to have its persistent notification on your phone, especially that's your primary device. 

## User guide
Note:
1. This app is designed to inform you through notification when the notification listening service is terminated (usually by the system) through a notification. This, however, will not work on Android 8 and above as notification posting those devices need to have notification channel to be implemented and I am too lazy to do it. :) Developers who are interested can implement it on MyNotificationListenerService class. 
2. Tested only on my own device. 

How to use:
1. go to Ifttt.com and activate the Google Assistant applet by grum235 at https://ifttt.com/applets/ArZBXqRz-turn-on-pc-with-google-assistant. 
2. Install ifttt on your phone and login to it. 
3. install this app on your phone and **manually grant notifcation access in settings**. 
4. input your pc mac address in the app. 
Done. 

## Developer Guide
1. Download Android Studio
2. Import this
3. You may need to update gradle version. 
4. Rock!

## ScreenShots
<img src="http://i.imgur.com/2YRd420.png" width="400">
<img src="http://i.imgur.com/w19cJ1s.png" width="400">


></content>
</snippet>
