# Change Log

## Version 1.1.2 (2016-06-20)
* New: Added helper methods to get device screen height and width

## Version 1.1.1 (2016-05-04)
* New: Added helper method to tint an ImageView
* New: Added helper method to tint the icon of a menu item

## Version 1.1.0 (2016-04-21)
* Update: Fixed bug where the tint was not getting correctly applied on a drawable in pre-Lollipop

## Version 1.0.31 (2016-04-18)
* New: Added method to check the user's internet connection based on the context

## Version 1.0.30 (2016-04-08)
* New: Added method to an attribute's resource Id to set it programmatically 

## Version 1.0.29 (2016-03-28)
* New: Added new helper method to create a 2-choice AlertDialog

## Version 1.0.28 (2016-03-25)
* New: Added method to quickly generate a multi-choice dialog

## Version 1.0.27 (2016-03-16)
* New: Added method to open PDFs

## Version 1.0.26 (2016-03-16)
* New: Added method to get the device manufacturer and model 
* Added check for `http://` or `https://` on a URL before trying to open it 

## Version 1.0.25 (2016-03-14)
* Removed the need for Timber for some utility classes 
* Removed Dagger dependency 

## Version 1.0.24 (2016-03-07)
* New: Added method to display a neutral AlertDialog with a message String and an OnClickListener for the button
* New: Added method to display a toast with a message String 

## Version 1.0.23 (2016-03-01)
* New: Added method to open the current app in the Play Store using the package name from the passed Context

## Version 1.0.22 (2016-02-25)
* Fix: Fixed the loadObject() method in the StorageUtils to work with a list of objects 

## Version 1.0.21 (2016-02-25)
* New: CustomDurationViewPager is a ViewPager instance that has a custom scroll animation speed 

## Version 1.0.20 (2016-02-24)
* New: Date/Time formatter methods can now take null parameters (they will return null in this case)
* Fix: Fixed short date formatter not being correctly initialized

## Version 1.0.19 (2016-02-23)
* New: Added methods to load and save objects to internal storage

## Version 1.0.18 (2016-02-22)
* Added method to get a BufferedSource for a raw resource

## Version 1.0.17 (2016-02-18)
* Added method to open an app on the Play Store 

## Version 1.0.16 (2016-02-12)
* Added method to get the short String for a date 

## Version 1.0.15 (2016-02-08)
* Fixed method name in DateUtils

## Version 1.0.14 (2016-02-08)
* Added method to get the short String for a time 

## Version 1.0.13 (2016-02-08)
* Added methods to check the future and past of a ZoneDateTime

## Version 1.0.12 (2016-02-08)
* Added some date utility methods
* Added a SharedPreferences helper class for dates

## Version 1.0.11 (2016-02-02)
* Added method to read a String from a file in the raw resource folder

## Version 1.0.10 (2016-02-02)
* Added Device class, with helper methods to determine the device's OS
* Added method to check connectivity 

## Version 1.0.9 (2016-01-29)
* Set up a base class for all preference utility classes 
* Added method to check if the preference is set

## Version 1.0.8 (2016-01-28)
* Added clear methods for the SharedPreferences utility classes 

## Version 1.0.7 (2016-01-28)
* Fixed bug with the scopes of the constructors of the Preference classes 

## Version 1.0.6 (2016-01-28)
* Added more AlertDialog helper methods
* Switched the Gradle dependencies to "provided"

## Version 1.0.5 (2016-01-28)
* Now returning the created AlertDialog in the DialogUtils statis helper methods. 

## Version 1.0.4 (2016-01-28)
* Added option to not specify a title and message for any type of Alert Dialog

## Version 1.0.3 (2016-01-27)
* Renamed the Utils class 

## Version 1.0.2 (2016-01-27)
* Renamed the DialogUtil class 

## Version 1.0.1 (2016-01-27)
* Added the non swipeable ViewPager

## Version 1.0.0 (2016-01-27)
* Initial Release. Includes: 
    * Timber tree for production builds
    * Shared Preferences helpers
    * Dialog helpers
    * Static utility class 