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
    id("com.diffplug.spotless") version "5.12.4"
    id("com.github.ben-manes.versions") version "0.38.0"
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
        // https://stackoverflow.com/a/66168563/3444398
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

/* Versions Configuration */

tasks.named(
    "dependencyUpdates",
    com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask::class.java
).configure {
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

    format("markdown") {
        target("**/*.md")
        trimTrailingWhitespace()
        endWithNewline()
        prettier().config(mapOf("parser" to "markdown", "tabWidth" to 4))
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
