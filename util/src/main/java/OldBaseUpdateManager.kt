/*
 * Copyright 2016-2019 Julien Guerinet
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

package com.guerinet.suitcase.util

import android.content.SharedPreferences

/**
 * Base class that manages any updates received from the app
 * This loops through all possible version codes. It should not be used for big
 * version codes, as the loop would take some time. Instead, please use the [BaseUpdateManager]
 * @author Julien Guerinet
 * @since 2.2.1
 *
 * @param prefs [SharedPreferences] instance where we can store/read the
 *                              version number
 * @param currentVersionCode Current version code of the app. Can be retrieved with
 *                              BuildConfig.VERSION_CODE
 * @param versionPrefName Name of the pref where the stored version is stored
 *                              (defaults to "version")
 */
abstract class OldBaseUpdateManager(
    private val prefs: SharedPreferences,
    private val currentVersionCode: Int,
    private val versionPrefName: String = "version"
) {

    /**
     * Returns True if an update is necessary, false otherwise
     */
    open fun needsUpdate(): Boolean = prefs.getInt(versionPrefName, -1) < currentVersionCode

    /**
     * Runs any update code if necessary
     */
    open fun update() {
        var storedCode = prefs.getInt(versionPrefName, -1)

        if (storedCode < currentVersionCode) {
            // Stored code is smaller than current version code: app has updated
            while (storedCode < currentVersionCode) {
                when (storedCode) {
                    -1 -> {
                        val breakLoop = firstOpen()
                        if (breakLoop) {
                            // If we need to break the loop, set the stored code to the current one
                            storedCode = currentVersionCode
                        }
                    }
                    else -> {
                        // Run any necessary update code. Update the stored with what is returned
                        storedCode = update(storedCode)
                    }
                }
                storedCode++
            }
        }

        // Store the new version code
        prefs.edit().putInt(versionPrefName, currentVersionCode).apply()
        onUpdateFinished()
    }

    /**
     * Called when an update has occurred and all of the update code has been run
     */
    open fun onUpdateFinished() {}

    /**
     * Called when this is the first time the user has opened the app
     * @return True if we should stop any update code after this, false if we should continue
     */
    abstract fun firstOpen(): Boolean

    /**
     * Runs any update code necessary for the given [version]
     * @return The version to continue the update loop from. This allows us to skip version updates
     *  or skip any other update by returning the current version. If the updates should continue
     *  in the same fashion, the given [version] should be returned.
     */
    abstract fun update(version: Int): Int
}
