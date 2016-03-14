# Android Utils

## Summary
Android utility classes and methods that I use in most of my Android projects

## Instructions
[![Release](https://jitpack.io/v/com.guerinet/android-utils.svg)](https://jitpack.io/#com.guerinet/android-utils)

To include this in your project, you can add it with Gradle by using [JitPack](https://jitpack.io). Replace X.X.X below with the latest version found on the status badge above or on the [Releases](https://github.com/jguerinet/android-utils/releases) page:

    repositories {
        maven { url "https://jitpack.io" }
    }

	dependencies {
	    compile 'com.guerinet:android-utils:X.X.X'
	}


## Gradle Dependencies
* [Android AppCompat](http://developer.android.com/tools/support-library/features.html#v7-appcompat)
* [Android Support v4](http://developer.android.com/tools/support-library/features.html#v4)
* [AndroidThreeTen](https://github.com/JakeWharton/ThreeTenABP)
* [Timber](https://github.com/JakeWharton/timber)
* [Okio](https://github.com/square/okio)

## A Note About the Gradle Dependencies
The Gradle dependencies are declared using the 'provided' scope, which means that they won't be included if you don't explicitly add them to your
`build.gradle` file. These following methods/classes need the following dependencies declared:

* The `DialogUtils` class needs AppCompat
* The `NonSwipeableViewPager` class needs support v4
* The `ProductionTree` class needs Timber  
* The `DateUtils` and `DatePreference` needs AndroidThreeTen
* In `Utils`, both `setTint()` need support v4
* In `Utils`, `stringFromRaw()` needs Okio
* In `Utils`, `isPermissionGranted()` and `requestPermission()` need support v4

## Contributors
* [Julien Guerinet](https://github.com/jguerinet)

## Version History
See the [Change Log](CHANGELOG.md).

## Copyright
	 Copyright 2016 Julien Guerinet

	 Licensed under the Apache License, Version 2.0 (the "License");
	 you may not use this file except in compliance with the License.
	 You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

	 Unless required by applicable law or agreed to in writing, software
	 distributed under the License is distributed on an "AS IS" BASIS,
	 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 See the License for the specific language governing permissions and
	 limitations under the License.
