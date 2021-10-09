/*
 * Copyright 2016-2021 Julien Guerinet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object Versions {

    const val SUITCASE = "7.0.3"
    const val KOTLIN = "1.5.31"

    object Plugins {
        const val ANDROID = "7.0.2"
        const val ANDROID_MAVEN = "2.1"
        const val SPOTLESS = "5.15.2"
        const val VERSIONS = "0.39.0"
    }

    const val COROUTINES = "1.5.2-native-mt"
    const val DESUGAR_JDK_LIBS = "1.1.5"
    const val FIREBASE = "28.4.1"
    const val FIREBASE_API = "18.0.3"
    const val KOIN = "3.1.2"
    const val KOTLINX_DATE_TIME = "0.3.0"
    const val MATERIAL_DIALOGS = "3.3.0"
    const val MULTIPLATFORM_SETTINGS = "0.7.6"
    const val OKIO = "2.10.0"
    const val TIMBER = "5.0.1"

    object AndroidX {
        const val ANNOTATION = "1.2.0"
        const val APPCOMPAT = "1.3.1"
        const val BROWSER = "1.3.0"
        const val CONTENT = "1.0.0"
        const val CORE_KTX = "1.6.0"
        const val LIFECYCLE = "2.3.1"
        const val RECYCLERVIEW = "1.2.1"
        const val ROOM = "2.3.0"
    }

    object Android {
        const val MIN_SDK = 21
        const val TARGET_SDK = 31
    }
}

object Deps {

    object Plugins {
        const val ANDROID = "com.android.tools.build:gradle:${Versions.Plugins.ANDROID}"
        const val ANDROID_MAVEN =
            "com.github.dcendents:android-maven-gradle-plugin:${Versions.Plugins.ANDROID_MAVEN}"
        const val SPOTLESS = "com.diffplug.spotless"
        const val VERSIONS = "com.github.ben-manes.versions"
    }

    const val DESUGAR_JDK_LIBS = "com.android.tools:desugar_jdk_libs:${Versions.DESUGAR_JDK_LIBS}"
    const val KOTLINX_DATE_TIME =
        "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KOTLINX_DATE_TIME}"
    const val MATERIAL_DIALOGS = "com.afollestad.material-dialogs:core:${Versions.MATERIAL_DIALOGS}"
    const val OKIO = "com.squareup.okio:okio:${Versions.OKIO}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    object Coroutines {
        const val ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    }

    object Firebase {
        private const val BASE = "com.google.firebase:firebase"
        const val BOM = "$BASE-bom:${Versions.FIREBASE}"
        const val ANALYTICS = "$BASE-analytics-ktx:${Versions.FIREBASE_API}"
    }

    object Koin {
        private const val BASE = "io.insert-koin:koin"
        const val ANDROID = "$BASE-android:${Versions.KOIN}"
        const val CORE = "$BASE-core:${Versions.KOIN}"
    }

    object MultiplatformSettings {
        private const val BASE = "com.russhwolf:multiplatform-settings"
        const val CORE = "$BASE:${Versions.MULTIPLATFORM_SETTINGS}"
        const val COROUTINES = "$BASE-coroutines-native-mt:${Versions.MULTIPLATFORM_SETTINGS}"
    }

    object AndroidX {
        private const val BASE = "androidx"
        const val ANNOTATION = "$BASE.annotation:annotation:${Versions.AndroidX.ANNOTATION}"
        const val APPCOMPAT =
            "$BASE.appcompat:appcompat:${Versions.AndroidX.APPCOMPAT}"
        const val BROWSER = "$BASE.browser:browser:${Versions.AndroidX.BROWSER}"
        const val CONTENT = "$BASE.appcompat:appcompat:${Versions.AndroidX.CONTENT}"
        const val CORE_KTX = "$BASE.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
        const val RECYCLERVIEW = "$BASE.recyclerview:recyclerview:${Versions.AndroidX.RECYCLERVIEW}"
        const val LIFECYCLE = "$BASE.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_COMPILER =
            "$BASE.lifecycle:lifecycle-compiler:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_LIVEDATA =
            "$BASE.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.LIFECYCLE}"
        const val ROOM = "$BASE.room:room-runtime:${Versions.AndroidX.ROOM}"
        const val ROOM_COMPILER = "$BASE.room:room-compiler:${Versions.AndroidX.ROOM}"
    }
}
