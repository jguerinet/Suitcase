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

    const val KOTLIN = "1.4.21"

    /* Plugins */
    object Plugins {
        const val ANDROID = "7.0.0-alpha05"
        const val GOOGLE_SERVICES = "4.3.4"
    }

    /* Common */
    const val COROUTINES = "1.4.2-native-mt"
    const val KARMOK = "0.1.8"
    const val KERMIT = "0.1.8"
    const val KOIN = "2.0.1"
    const val KOTLINX_DATE_TIME = "0.1.1"
    const val MATERIAL_DIALOGS = "3.3.0"
    const val OKIO = "2.2.2"
    const val TIMBER = "4.7.1"

    object AndroidX {
        const val ANNOTATION = "1.1.0"
        const val APPCOMPAT = "1.2.0"
        const val CONTENT = "1.0.0"
        const val COMPOSE = "1.0.0-alpha09"
        const val CONSTRAINT_LAYOUT = "2.0.4"
        const val CORE_KTX = "1.3.2"
        const val LIFECYCLE = "2.2.0"
        const val RECYCLERVIEW = "1.1.0"
        const val ROOM = "2.1.0"

        object Test {
            const val TEST = "1.3.0"
            const val TEST_EXT = "1.1.2"
        }
    }

    /* Android app versions */
    object Android {
        const val MIN_SDK = 21
        const val TARGET_SDK = 30

        const val FIREBASE = "26.3.0"
        const val MATERIAL = "1.2.1"

        /* Test versions */
        object Test {
            const val JUNIT = "4.13.1"
            const val ROBOLECTRIC = "4.4"
        }
    }
}

object Deps {

    object Plugins {
        const val ANDROID = "com.android.tools.build:gradle:${Versions.Plugins.ANDROID}"
        const val GOOGLE_SERVICES =
            "com.google.gms:google-services:${Versions.Plugins.GOOGLE_SERVICES}"
        const val KOTLINX_SERIALIZATION =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}"
    }

    const val KARMOK = "co.touchlab:karmok-library:${Versions.KARMOK}"
    const val KERMIT = "co.touchlab:kermit:${Versions.KERMIT}"
    const val KOTLINX_DATE_TIME =
        "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KOTLINX_DATE_TIME}"
    const val MATERIAL_DIALOGS = "com.afollestad.material-dialogs:core:${Versions.MATERIAL_DIALOGS}"
    const val OKIO = "com.squareup.okio:okio:${Versions.OKIO}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    object Coroutines {
        const val COMMON = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
        const val ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
        const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
    }

    object Firebase {
        private const val BASE = "com.google.firebase:firebase"
        const val BOM = "$BASE-bom:${Versions.Android.FIREBASE}"
        const val ANALYTICS = "$BASE-analytics-ktx"
    }

    object Koin {
        const val ANDROID = "org.koin:koin-android:${Versions.KOIN}"
        const val ANDROID_VIEW_MODEL = "org.koin:koin-androidx-viewmodel:${Versions.KOIN}"
        const val CORE = "org.koin:koin-core:${Versions.KOIN}"
        const val TEST = "org.koin:koin-test:${Versions.KOIN}"
    }

    object KotlinTest {
        const val ANNOTATIONS =
            "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.KOTLIN}"
        const val COMMON = "org.jetbrains.kotlin:kotlin-test-common:${Versions.KOTLIN}"
        const val JVM = "org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}"
        const val JUNIT = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.KOTLIN}"
    }

    object AndroidX {
        private const val BASE = "androidx"
        const val ANNOTATION = "$BASE.annotation:annotation:${Versions.AndroidX.ANNOTATION}"
        const val APPCOMPAT =
            "$BASE.appcompat:appcompat:${Versions.AndroidX.APPCOMPAT}"
        const val CONTENT =
            "$BASE.appcompat:appcompat:${Versions.AndroidX.CONTENT}"
        const val COMPOSE = "androidx.compose.ui:ui:${Versions.AndroidX.COMPOSE}"
        const val COMPOSE_TOOLING =
            "androidx.compose.ui:ui-tooling:${Versions.AndroidX.COMPOSE}"
        const val COMPOSE_FOUNDATION =
            "androidx.compose.foundation:foundation:${Versions.AndroidX.COMPOSE}"
        const val COMPOSE_MATERIAL =
            "androidx.compose.material:material:${Versions.AndroidX.COMPOSE}"
        const val COMPOSE_MATERIAL_ICONS =
            "androidx.compose.material:material-icons-core:${Versions.AndroidX.COMPOSE}"
        const val COMPOSE_LIVE_DATA =
            "androidx.compose.runtime:runtime-livedata:${Versions.AndroidX.COMPOSE}"
        const val CORE_KTX = "$BASE.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.CONSTRAINT_LAYOUT}"
        const val RECYCLERVIEW =
            "androidx.recyclerview:recyclerview:${Versions.AndroidX.RECYCLERVIEW}"
        const val LIFECYCLE_VIEWMODEL =
            "androidx.lifecycle:lifecycle-viewmodel:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_LIVEDATA =
            "androidx.lifecycle:lifecycle-livedata:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_COMPILER =
            "$BASE.lifecycle:lifecycle-compiler:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_EXT =
            "$BASE.lifecycle:lifecycle-extensions:${Versions.AndroidX.LIFECYCLE}"
        const val ROOM = "$BASE.room:room-runtime:${Versions.AndroidX.ROOM}"
        const val ROOM_COMPILER = "$BASE.room:room-compiler:${Versions.AndroidX.ROOM}"

        object Test {
            const val CORE = "androidx.test:core:${Versions.AndroidX.Test.TEST}"
            const val JUNIT =
                "androidx.test.ext:junit:${Versions.AndroidX.Test.TEST_EXT}"
            const val RUNNER = "androidx.test:runner:${Versions.AndroidX.Test.TEST}"
            const val RULES = "androidx.test:rules:${Versions.AndroidX.Test.TEST}"
        }
    }

    /* Dependencies for the Android app */
    object Android {

        const val MATERIAL = "com.google.android.material:material:${Versions.Android.MATERIAL}"

        /* Test dependencies */
        object Test {
            const val JUNIT = "junit:junit:${Versions.Android.Test.JUNIT}"
            const val ROBOLECTRIC =
                "org.robolectric:robolectric:${Versions.Android.Test.ROBOLECTRIC}"
        }
    }
}
