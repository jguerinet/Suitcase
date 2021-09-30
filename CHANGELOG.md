# Change Log

## Version 7.0.1 (2021-09-29)

- `date-android`:
    - Added a method to get a date and time String from an `Instant`

## Version 7.0.0 (2021-09-28)

- Updated all dependencies to their latest versions
- Separated the `date` module into `date` and `date-android` in preparation for KMP
- Switched the `prefs` module to be `settings` to use the `MultiplatformSettings` library in
  preparation for KMP

## Version 6.0.0 (2021-05-02)

- Updated all dependencies to their latest versions
- Restructured the Gradle scripts (using Kotlin DSL and `buildSrc`)
- Updated the publishing action for all of the modules
- Added function to display a toast (It was removed from AndroidX)

## Version 5.2.0 (2019-07-03)

- Updated all dependencies to their latest versions
- `ui`:
    - Added extension function to `ImageView` to set an image resource using the Compat functions
    - Added extension to `EditText` to set the IME action button and listener
    - Added extension to `TextView` to set the text color using the Compat functions and a color Id
- `util`:
    - Added extension function to convert a dp value to pixels
    - Added fallback on opening a Chrome custom tab to use the opening of a normal Url

## Version 5.1.1 (2019-02-28)

-   `prefs`:
    -   Pulled out the `SharedPrefLiveData` into a separate class to be used by others
    -   Made the `liveData()` function on the `BasePref` open

## Version 5.1.0 (2019-02-28)

-   `prefs`:
    -   Added a `liveData()` method on the prefs objects to make them observable.
-   `ui`:
    -   Removed the `RecyclerView` adapter implementation from v5.0.0 as it wasn't working out

## Version 5.0.1 (2019-02-22)

-   `dialog`:
    -   Added extension functions to quickly add "Ok" and "Cancel" buttons to a dialog

## Version 5.0.0 (2019-02-21)

-   `analytics`:
    -   Added interface for Analytics related functions
    -   Removed old GA stuff
-   `dialog`:
    -   Updated to Material Dialogs v2.0
-   `ui`:
    -   Added new `RecyclerView` adapter implementation for easier instantiation
-   `coroutines`:
    -   Changed default job to `SupervisorJob`
-   Updated dependencies
-   Added Spotless
-   Started using Codacy

## Version 4.9.0 (2018-12-10)

-   `log`:
    -   Add the `TimberTag` interface, which allows you to easily add tags to every Timber log that implements that class

## Version 4.8.1 (2018-11-29)

-   `coroutines`:
    -   Renamed the `CoroutineActivity`

## Version 4.8.0 (2018-11-29)

-   `analytics`:
    -   Deprecated `GAManager` in favor of Firebase
-   `coroutines`:
    -   Added the `BetterCoroutineScope`, which is a `CoroutineScope` with a cancel method
    -   Renamed the `MainScope`
    -   Changed the `ViewModel` classes to use the new `MainScope`
    -   Added the `CoroutinesActivity` that uses the `MainScope`
-   `ui`:
    -   Removed pre-21 compatibility when setting the `Drawable` tint
-   `util`:
    -   Cleaned up the `Device` object: Switched to API numbers instead of names, switched functions to variables

## Version 4.7.2 (2018-11-23)

-   `coroutines`:
    -   Fixed the coroutine dependency used (Android specific)

## Version 4.7.1 (2018-11-23)

-   `coroutines`:
    -   Move the `bind()` method into the `LifeycleOwnerUIScope`
-   `util`:
    -   Add explicit `ActivityNotFoundException` throwing for when opening a url or custom tab

## Version 4.7.0 (2018-11-23)

-   `coroutines`:
    -   New module, with useful extension functions and classes to use with Kotlin coroutines

## Version 4.6.0 (2018-11-20)

-   `firebase-analytics`:
    -   New module, with useful extension functions to use Google Analytics for Firebase within your app

## Version 4.5.2 (2018-11-02)

-   `io`:
    -   Further bug fixes for v4.5.1, including a method name change for `getFileUri()`

## Version 4.5.1 (2018-11-02)

-   `io`:
    -   Bug fix for v4.5.0

## Version 4.5.0 (2018-11-02)

-   `io`:
    -   Added `Context` extension to get the `Uri` for a file
    -   Added `Context` extension to create and write contents to a file
    -   Changed order of parameters on `getFile()`
    -   Added a provider paths Xml to cover the default case

## Version 4.4.1 (2018-10-31)

-   `room`:
    -   Added `TypeConverter`s for `MutableList<T>`, with convenience converters for String and Int
    -   Added convenience method to log an `AppUpdate` based on its name and code

## Version 4.4.0 (2018-10-31)

-   `room`:
    -   Added the `AppUpdate` class and its Db/Dao to keep track of when the app is updated

## Version 4.3.2 (2018-10-30)

-   `log`:
    -   Added a logger for `Koin`

## Version 4.3.1 (2018-10-29)

-   `log`:
    -   Added the logging of the `priority`

## Version 4.3.0 (2018-10-29)

-   Updated to Kotlin 1.3
-   Changed min SDK version to 21

## Version 4.2.1 (2018-10-25)

-   `room`:
    -   Moved the `Dao`s and the `TypeConverter`s to separate packages
    -   Added the `BaseUpdateDao` and the `BaseInsertDao` if you just need one subcategory of default methods
    -   Changed the conflict strategy of the `INSERT` functions to be `REPLACE`

## Version 4.2.0 (2018-10-23)

-   `room`:
    -   Added the `BaseDao` for basic functions on one model type
    -   Added the `BaseConverter` for a basic `TypeConverter` implementation
    -   Added the `BaseListConverter` for a basic list `TypeConverter` implementation
    -   Added a `TypeConverter` for `LocalTime`s

## Version 4.1.2 (2018-10-10)

-   `util`:
    -   Minor updates to the new `BaseUpdateManager`

## Version 4.1.1 (2018-10-10)

-   `util`:
    -   Minor updates to the new `BaseUpdateManager`

## Version 4.1.0 (2018-10-10)

-   `util`:
    -   Added the new `BaseUpdateManager` that works off of `Migration` classes
    -   Renamed old `BaseUpdateManager` to `OldBaseUpdateManager` (breaking)
    -   Switched to Kotlin folder conventions for this module

## Version 4.0.0 (2018-09-29)

-   Updated all libraries, including switching over to AndroidX
-   `lifecycle`:
    -   Added module for the Android Lifecycle components, with an extension to observe on a LifecycleOwner.
-   `room`:
    -   Added module for Android Room, with some common `TypeConverter`s
-   `util`:
    -   Removed the connection info from the debug info, moved it from an extension to a static method

## Version 3.1.1 (2018-08-10)

-   `dialog`:
    -   Changed the order of the params in the list dialog functions to have all defaulted params at the end.

## Version 3.1.0 (2018-08-10)

-   `dialog`
    -   Changed all the interfaces to lambdas
    -   Moved all lambdas to be the last parameter in the functions

## Version 3.0.0 (2018-08-09)

-   Removed the `db` module completely
-   `date`:
    -   Added option to specify the locale on all formatters
    -   Changed `isPast`, `isFuture`, and `rfc1123String` methods to be variables instead of functions
    -   Added `isToday` variables
    -   Removed the old setter and getter methods on the `Pref` classes. You should access the `date` property directly
-   `dialog`:
    -   Removed all previously deprecated classes/methods
    -   For the list dialogs, all params are now taken directly instead of having a separate interface to implement
-   `io`:
    -   Removed all previously deprecated classes/methods
-   `log`:
    -   Added the `tag` value to the function that logs a message
-   `prefs`
    -   Removed the old setter and getter methods on the `Pref` classes. You should access the `value` property directly
-   `ui`:
    -   Removed all previously deprecated classes/methods
    -   Added function to set an `ImageView` tint using a color resource Id
    -   Added function to set a `TextView`'s compound `Drawable` tint using a color resource Id
-   `util`:
    -   Removed all previously deprecated classes/methods
    -   Changed device measurement functions to be extensions

## Version 2.6.10 (2018-07-26)

-   `ui`:
    -   Big fix of v2.6.9

## Version 2.6.9 (2018-07-26)

-   `ui`:
    -   Added methods to set the drawables on a `TextView` as named parameters

## Version 2.6.8 (2018-07-12)

-   Library updates
-   `ui`:
    -   Added method to simplify watching an `EditText`

## Version 2.6.7 (2018-06-15)

-   `util`:
    -   Made `showEmptyView()` methods on the adapters `open`

## Version 2.6.6 (2018-05-25)

-   `util`:
    -   Added a method to wrap a `Context` with a `Locale` to change the app language

## Version 2.6.5 (2018-05-24)

-   `util`:
    -   Added option to add custom info to the debug info

## Version 2.6.4 (2018-05-24)

-   `util`:
    -   Added extension to get the color from a `Context` in a backwards compatible manner

## Version 2.6.3 (2018-05-11)

-   `util`:
    -   Added method to get debug info for feedback/bug reports

## Version 2.6.2 (2018-05-10)

-   `date`:
    -   Changed the `Temporal` extensions to be for `TemporalAccessor`s instead

## Version 2.6.1 (2018-04-30)

-   `ui`:
    -   Fix wrong call to get the `TextView`'s compound drawables when tinting

## Version 2.6.0 (2018-04-26)

-   `prefs` & `date`:
    -   Created nullable and non-nullable variations of every preference helper class
        -   Breaking: `StringPref`, `DatePref`, and `LocalDatePref` are no longer nullable. In order to continue using them as nullable, use their `NullXPref` equivalents

## Version 2.5.0 (2018-04-25)

-   `analytics`:
    -   Convert `isDisabled` in `GAManager` to a property
-   `dialog`:
    -   Made all `DialogUtil` methods extensions and deprecated the old one
-   `util`:
    -   Added a default name for the version pref name in the `BaseUpdateManager`
    -   Added a method that is called when an update is finished in the `BaseUpdateManager`
    -   Added a default file type (null) for `context.getFile()`

## Version 2.4.1 (2018-04-23)

-   `ui`:
    -   Added extension to set a `View`'s padding with a dimension Id
    -   Added extension to set a `TextView`'s text size with a dimension Id

## Version 2.4.0 (2018-04-14)

-   `io`:
    -   Deprecated the `IOUtils`, replacing it with extensions
-   `prefs`/`date`:
    -   Removed a bunch of redundant code within the pref classes
    -   Added backing properties to the pref classes
-   `util`:
    -   Made connectivity methods extensions
    -   Moved folder operations to extensions
    -   Moved permission operations to extensions / `Util` class

## Version 2.3.1 (2018-04-06)

-   Removed methods that are now part of [Android KTX](https://github.com/android/android-ktx)

## Version 2.3.0 (2018-04-06)

-   Updated all dependencies
-   Switched min SDK to 17, target SDK to 27
-   `date`:
    -   Made all date formatting methods extensions
    -   Added code to allow ranges of dates
-   `ui`:
    -   Added a `BaseListAdapter`
    -   Made all tinting methods extensions
    -   Breaking: `TextView` drawable tinting now uses relative positioning
-   `util`:
    -   Added a String extension to get a `Spanned` from an Html String
    -   Made all `Context` util methods extensions

## Version 2.2.2 (2017-12-26)

-   `util`:
    -   Made the `BaseUpdateManager` methods `open`

## Version 2.2.1 (2017-12-26)

-   Updated Kotlin
-   `util`:
    -   Added the `BaseUpdateManager` class, to serve as a base for running update code

## Version 2.2.0 (2017-11-29)

-   Updated all of the dependencies (notably support library)
-   Bumped target to 26
-   `util`:
    -   Added a method to check if a Permission has been granted

## Version 2.1.2 (2017-10-23)

-   `dialog`:
    -   Fixed the multi list dialog button type

## Version 2.1.1 (2017-09-23)

-   `analytics`
    -   Switched `isDisabled()` to be a method instead of a variable

## Version 2.1.0 (2017-08-31)

-   `analytics`
    -   Added the Analytics module for Google Analytics helper methods

## Version 2.0.8 (2017-07-25)

-   `date`:
    -   Added missing static annotation

## Version 2.0.7 (2017-07-25)

-   `date`:
    -   Switched some of the formatting Strings to take in `TemporalAccessor`s instead of `Temporal`s
    -   Added formatting method to return the full date String

## Version 2.0.6 (2017-07-01)

-   `util`:
    -   Fixed bug where opening a Url in a Chrome custom tab would crash the app if the scheme was missing from the Url

## Version 2.0.5 (2017-06-28)

-   `util`:
    -   Added method to open a Url within a Chrome custom tab

## Version 2.0.4 (2017-05-31)

-   `log`:
    -   Made the abstract methods protected within the `ProductionTree`

## Version 2.0.3 (2017-05-31)

-   `ui`:
    -   Made the utility methods static in Java
-   `util`:
    -   Overloaded some methods in the `Utils` class for Java

## Version 2.0.2 (2017-05-31)

-   `dialog`:
    -   Fixed order parameters to work better in Java

## Version 2.0.1 (2017-05-31)

-   `date`:
    -   Changed all of the `XXXPref` methods to open
-   `dialog`:
    -   Added some `@JvmOverloads` to the right methods in `DialogUtils`
-   `prefs`:
    -   Changed all of the `XXXPref` methods to open

## Version 2.0.0 (2017-05-31)

-   Converted everything to Kotlin!
-   Renamed the repo
-   Broke everything out into separate modules (see the README for more info)
-   Dependencies now come bundled in instead of expecting them to be included by the different projects

## Version 1.2.1 (2017-02-28)

-   Added method to get a randomly generated UUID

## Version 1.2.0 (2017-02-21)

-   Added the `RecyclerViewBaseAdapter`, a base adapter for a `RecyclerView` with a built-in ViewHolder and empty view support

## Version 1.1.7 (2016-12-08)

-   Fixed the opening of the PDF for Nougat +

## Version 1.1.6 (2016-09-15)

-   New: Added method to check if we are on Nougat or higher
-   Renamed the OS version check method names to be clearer

## Version 1.1.5 (2016-08-22)

-   New: Added method to check if a given version is more recent than the current version

## Version 1.1.4 (2016-07-27)

-   New: Added a LocalDatePreference to store LocalDates in SharedPreferences

## Version 1.1.3 (2016-07-22)

-   New: Added method to put the logs into a given file (used for bug reports)

## Version 1.1.2 (2016-06-20)

-   New: Added helper methods to get device screen height and width

## Version 1.1.1 (2016-05-04)

-   New: Added helper method to tint an ImageView
-   New: Added helper method to tint the icon of a menu item

## Version 1.1.0 (2016-04-21)

-   Update: Fixed bug where the tint was not getting correctly applied on a drawable in pre-Lollipop

## Version 1.0.31 (2016-04-18)

-   New: Added method to check the user's internet connection based on the context

## Version 1.0.30 (2016-04-08)

-   New: Added method to an attribute's resource Id to set it programmatically

## Version 1.0.29 (2016-03-28)

-   New: Added new helper method to create a 2-choice AlertDialog

## Version 1.0.28 (2016-03-25)

-   New: Added method to quickly generate a multi-choice dialog

## Version 1.0.27 (2016-03-16)

-   New: Added method to open PDFs

## Version 1.0.26 (2016-03-16)

-   New: Added method to get the device manufacturer and model
-   Added check for `http://` or `https://` on a URL before trying to open it

## Version 1.0.25 (2016-03-14)

-   Removed the need for Timber for some utility classes
-   Removed Dagger dependency

## Version 1.0.24 (2016-03-07)

-   New: Added method to display a neutral AlertDialog with a message String and an OnClickListener for the button
-   New: Added method to display a toast with a message String

## Version 1.0.23 (2016-03-01)

-   New: Added method to open the current app in the Play Store using the package name from the passed Context

## Version 1.0.22 (2016-02-25)

-   Fix: Fixed the loadObject() method in the StorageUtils to work with a list of objects

## Version 1.0.21 (2016-02-25)

-   New: CustomDurationViewPager is a ViewPager instance that has a custom scroll animation speed

## Version 1.0.20 (2016-02-24)

-   New: Date/Time formatter methods can now take null parameters (they will return null in this case)
-   Fix: Fixed short date formatter not being correctly initialized

## Version 1.0.19 (2016-02-23)

-   New: Added methods to load and save objects to internal storage

## Version 1.0.18 (2016-02-22)

-   Added method to get a BufferedSource for a raw resource

## Version 1.0.17 (2016-02-18)

-   Added method to open an app on the Play Store

## Version 1.0.16 (2016-02-12)

-   Added method to get the short String for a date

## Version 1.0.15 (2016-02-08)

-   Fixed method name in DateUtils

## Version 1.0.14 (2016-02-08)

-   Added method to get the short String for a time

## Version 1.0.13 (2016-02-08)

-   Added methods to check the future and past of a ZoneDateTime

## Version 1.0.12 (2016-02-08)

-   Added some date utility methods
-   Added a SharedPreferences helper class for dates

## Version 1.0.11 (2016-02-02)

-   Added method to read a String from a file in the raw resource folder

## Version 1.0.10 (2016-02-02)

-   Added Device class, with helper methods to determine the device's OS
-   Added method to check connectivity

## Version 1.0.9 (2016-01-29)

-   Set up a base class for all preference utility classes
-   Added method to check if the preference is set

## Version 1.0.8 (2016-01-28)

-   Added clear methods for the SharedPreferences utility classes

## Version 1.0.7 (2016-01-28)

-   Fixed bug with the scopes of the constructors of the Preference classes

## Version 1.0.6 (2016-01-28)

-   Added more AlertDialog helper methods
-   Switched the Gradle dependencies to "provided"

## Version 1.0.5 (2016-01-28)

-   Now returning the created AlertDialog in the DialogUtils static helper methods.

## Version 1.0.4 (2016-01-28)

-   Added option to not specify a title and message for any type of Alert Dialog

## Version 1.0.3 (2016-01-27)

-   Renamed the Utils class

## Version 1.0.2 (2016-01-27)

-   Renamed the DialogUtil class

## Version 1.0.1 (2016-01-27)

-   Added the non swipeable ViewPager

## Version 1.0.0 (2016-01-27)

-   Initial Release. Includes:
    -   Timber tree for production builds
    -   Shared Preferences helpers
    -   Dialog helpers
    -   Static utility class
