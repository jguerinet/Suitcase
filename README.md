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
* `com.guerinet.suitcase:date`: Date utility methods and classes, uses [AndroidThreeTen][1]
* `com.guerinet.suitcase:db`: DBFlow utility methods and classes, uses [AndroidThreeTen][1] and [DBFlow][2]
* `com.guerinet.suitcase:dialog`: Dialog utility methods, uses [MaterialDialogs][3]
* `com.guerinet.suitcase:io`: I/O utility methods, uses [Okio][4]
* `com.guerinet.suitcase:log`: Logging utility methods and classes, uses [Timber][5]
* `com.guerinet.suitcase:prefs`: `SharedPreferences` utility methods and classes
* `com.guerinet.suitcase:ui`: UI utility methods and classes
* `com.guerinet.suitcase:util`: Basic utility methods and resources

[1]:https://github.com/JakeWharton/ThreeTenABP
[2]:https://github.com/Raizlabs/DBFlow
[3]:https://github.com/afollestad/material-dialogs
[4]:https://github.com/square/okio
[5]:https://github.com/JakeWharton/timber

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
