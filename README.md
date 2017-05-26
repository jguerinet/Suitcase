# Suitcase
[![Release](https://jitpack.io/v/com.guerinet/suitcase.svg)](https://jitpack.io/#com.guerinet/suitcase)

## Summary
Android utility classes and methods that I use in all of my projects. When off on a coding adventure, don't forget to pack your suitcase!

## Instructions
To include this in your project, you can add it with Gradle by using [JitPack](https://jitpack.io). Replace X.X.X below with the latest version found on the status badge above:

    repositories {
        maven { url "https://jitpack.io" }
    }

	dependencies {
	    compile 'com.guerinet:suitcase:X.X.X'
	}

You can either include all of the different modules with the code above, or choose which modules to include:
* `com.guerinet.suitcase:date`: Date utility methods and classes, uses [AndroidThreeTen](https://github.com/JakeWharton/ThreeTenABP)
* `com.guerinet.suitcase:log`: Logging utility methods and classes, uses [Timber](https://github.com/JakeWharton/timber)
* `com.guerinet.suitcase:prefs`: `SharedPreferences` utility methods and classes

## Gradle Dependencies
* [Android AppCompat](http://developer.android.com/tools/support-library/features.html#v7-appcompat)
* [Android Support v4](http://developer.android.com/tools/support-library/features.html#v4)
* [Okio](https://github.com/square/okio)

## A Note About the Gradle Dependencies
The Gradle dependencies are declared using the 'provided' scope, which means that they won't be included if you don't explicitly add them to your
`build.gradle` file. These following methods/classes need the following dependencies declared:

* The `DialogUtils` class needs AppCompat
* The `NonSwipeableViewPager` class needs support v4
* The `RecyclerViewBaseAdapter` needs supportv7-recyclerview and Timber
* In `Utils`, both `setTint()` need support v4
* In `Utils`, `stringFromRaw()` needs Okio
* In `Utils`, `isPermissionGranted()` and `requestPermission()` need support v4

## Contributors
* [Julien Guerinet](https://github.com/jguerinet)

## Version History
See the [Change Log](CHANGELOG.md).

## Copyright
	 Copyright 2016-2017 Julien Guerinet

	 Licensed under the Apache License, Version 2.0 (the "License");
	 you may not use this file except in compliance with the License.
	 You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

	 Unless required by applicable law or agreed to in writing, software
	 distributed under the License is distributed on an "AS IS" BASIS,
	 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 See the License for the specific language governing permissions and
	 limitations under the License.
