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
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    `maven-publish`
}

android {
    compileSdk = Versions.Android.TARGET_SDK

    defaultConfig {
        minSdk = Versions.Android.MIN_SDK
        targetSdk = Versions.Android.TARGET_SDK
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Deps.AndroidX.BROWSER)
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

afterEvaluate {
    publishing {
        val group: String by project
        val artifact_name: String by project
        val url_site: String by project
        val license_name: String by project
        val license_url: String by project
        val developer_id: String by project
        val developer_name: String by project
        val developer_email: String by project
        val url_git: String by project

        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                run {
                    // Main Artifact
                    from(components.findByName("release"))
                    artifact(sourcesJar.get())
                    groupId = group
                    artifactId = artifact_name
                    version = Versions.SUITCASE
                }

                pom.withXml {
                    asNode().apply {
                        appendNode("name", artifact_name)
                        appendNode("url", url_site)

                        appendNode("licenses").appendNode("license").apply {
                            appendNode("name", license_name)
                            appendNode("url", license_url)
                        }

                        appendNode("developers").appendNode("developer").apply {
                            appendNode("id", developer_id)
                            appendNode("name", developer_name)
                            appendNode("email", developer_email)
                        }

                        appendNode("scm").apply {
                            appendNode("connection", url_git)
                            appendNode("developerConnection", url_git)
                            appendNode("url", url_site)
                        }
                    }
                }
            }
        }
    }
}
