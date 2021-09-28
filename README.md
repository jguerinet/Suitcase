# Suitcase

[![Release](https://jitpack.io/v/com.guerinet/suitcase.svg)](https://jitpack.io/#com.guerinet/suitcase)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2d57fb43931b44309a34b525dd2ad513)](https://www.codacy.com/app/jguerinet/Suitcase?utm_source=github.com&utm_medium=referral&utm_content=jguerinet/Suitcase&utm_campaign=Badge_Grade)

## Summary

Android utility classes and methods that I use in all of my projects. When off on a coding
adventure, don't forget to pack your suitcase!

Note: We are slowly moving to start supporting Kotlin Multiplatform (KMP).

## Instructions

To include this in your project, you can add it with Gradle by using [JitPack](https://jitpack.io).
Replace X.X.X below with the latest version found on the status badge above:

    repositories {
        maven { url "https://jitpack.io" }
    }

    def suitcase_version = 'X.X.X'

    dependencies {

        // Analytics interface, used by firebase-analytics.
        implementation "com.guerinet.suitcase:analytics:$suitcase_version"

        // Coroutines extension functions and models, uses Kotlin Coroutines
        implementation "com.guerinet.suitcase:coroutines:$suitcase_version"

        // Date utility methods and classes, uses Kotlinx DateTime
        implementation "com.guerinet.suitcase:date:$suitcase_version"
        
        // Date utility formatting methods and classes specifically for Android, uses Kotlinx DateTime
        implementation "com.guerinet.suitcase:date-android:$suitcase_version"

        // Dialog utility methods, uses Material Dialogs
        implementation "com.guerinet.suitcase:dialog:$suitcase_version"

        // Firebase analytics extension functions, uses Firebase
        implementation "com.guerinet.suitcase:firebase-analytics:$suitcase_version"

        // I/O utility methods, uses Okio
        implementation "com.guerinet.suitcase:io:$suitcase_version"

        // Lifecycle utility methods, uses the Android Lifecycle components
        implementation "com.guerinet.suitcase:lifecycle:$suitcase_version"

        // Logging utility methods and classes, uses Timber. Also a Logger for Koin, but Koin is explicitly needed as a dependency
        implementation "com.guerinet.suitcase:log:$suitcase_version"

        // Key-value utility methods and classes (i.e. SharedPreferences on Android), uses Multiplatform Settings
        implementation "com.guerinet.suitcase:settings:$suitcase_version"

        // Room utility methods, uses Room
        implementation "com.guerinet.suitcase:room:$suitcase_version"

        // UI utility methods and classes
        implementation "com.guerinet.suitcase:ui:$suitcase_version"

        // Basic utility methods and resources
        implementation "com.guerinet.suitcase:util:$suitcase_version"
    }

- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Kotlinx DateTime](https://github.com/Kotlin/kotlinx-datetime)
- [Material Dialogs](https://github.com/afollestad/material-dialogs)
-   [Firebase](https://firebase.google.com/docs/analytics/)
-   [Okio](https://github.com/square/okio)
-   [Timber](https://github.com/JakeWharton/timber)
- [Koin](https://github.com/InsertKoinIO/koin)
- [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings)

## Contributors

-   [Julien Guerinet](https://github.com/jguerinet)

## Version History

See the [Change Log](CHANGELOG.md).

## Copyright

     Copyright 2016-2021 Julien Guerinet

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
