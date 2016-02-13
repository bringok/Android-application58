# Android-application
Application developed in Java with Android Studio

Application developed during the course taught by the society [Bite S.r.l.](http://bitesrl.it/)
It implements lists, http connection with JSON parse, local storage with Shared preferences and mySQL, menu inflating and the usage of Google Maps.

How it works: 
Once started it connects to a remote server to get informations about the cities shown in the list. It saves these informations 
in a local database and checks in the future launches if it already has them, in order to not reconnect to the server. Tapping on a city, it will open a map
with a marker on the city selected using GPS coordinates retrieved from the server.
If a long click is performed on a city in the list, it will be saved in Shared Preferences. This is notificated with a Toast.
The menu button is used to re-load the informations from the server.


