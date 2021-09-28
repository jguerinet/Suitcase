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

plugins {
    id(Deps.Plugins.SPOTLESS) version Versions.Plugins.SPOTLESS
    id(Deps.Plugins.VERSIONS) version Versions.Plugins.VERSIONS
}

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Deps.Plugins.ANDROID)
        classpath(Deps.Plugins.ANDROID_MAVEN)
        classpath(kotlin("gradle-plugin", Versions.KOTLIN))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx/")
    }
}

/* Versions Configuration */

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    // Don't allow unstable versions if the current version is stable
    rejectVersionIf {
        isUnstable(candidate.version) && !isUnstable(currentVersion)
    }
}

/**
 * Returns true if the [version] is unstable, false otherwise
 */
fun isUnstable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    return !stableKeyword && !regex.matches(version)
}

/* Spotless Configuration */

spotless {

    format("misc") {
        target("**/.gitignore")
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target("**/*.kt")
        // See https://github.com/pinterest/ktlint/issues/527
        ktlint("0.41.0")
            .userData(mapOf("ij_kotlin_imports_layout" to "*,java.**,javax.**,kotlin.**"))
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        // See https://github.com/pinterest/ktlint/issues/527
        ktlint("0.41.0")
            .userData(mapOf("ij_kotlin_imports_layout" to "*,java.**,javax.**,kotlin.**"))
        trimTrailingWhitespace()
        endWithNewline()
    }
}
