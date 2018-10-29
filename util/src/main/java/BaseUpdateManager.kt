/*
 * Copyright 2016-2018 Julien Guerinet
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
 * @author Julien Guerinet
 * @since 4.1.0
 *
 * @param prefs                 [SharedPreferences] instance where we can store/read the
 *                              version number
 * @param currentVersionCode    Current version code of the app. Can be retrieved with
 *                              BuildConfig.VERSION_CODE
 * @param versionPrefName       Name of the pref where the stored version is stored
 *                              (defaults to "version")
 */
open class BaseUpdateManager(
    private val prefs: SharedPreferences,
    private val currentVersionCode: Int,
    private val versionPrefName: String = "version"
) {

    /** Stored version code, [FIRST_OPEN] if none stored */
    private val storedCode: Int
        get() = prefs.getInt(versionPrefName, FIRST_OPEN)

    /** List of [Migration]s to potentially run during an update */
    open val migrations = mutableListOf<Migration>()

    /**
     * Returns true if an update is necessary, false otherwise
     */
    open fun needsUpdate(): Boolean = storedCode < currentVersionCode

    /**
     * Runs any update code if necessary
     */
    open fun update() {
        var storedCode = storedCode

        if (storedCode < currentVersionCode) {
            // Stored code is smaller than current version code: app has updated
            migrations
                // Sort the migrations (in case they were given unsorted)
                .sortedWith(Comparator { o1, o2 -> o1.versionCode - o2.versionCode })
                .forEach {
                    if (it.versionCode < storedCode) {
                        // If the migration version code is older than the stored code, continue
                        return@forEach
                    }
                    // Run the block, update the stored code with what is returned
                    storedCode = it.block(it.versionCode)
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
     * Migration code to run on app update
     *
     * @param versionCode   Version this migration should be run for
     * @param block         Migration to run. Returns the version code to continue any migrations
     *                      with, allowing us to skip other migrations. If the migrations should
     *                      run normally, simply return this migration's version code. If no other
     *                      migration code should run (ex: first open), return the given version
     *                      code.
     */
    class Migration(val versionCode: Int, val block: (versionCode: Int) -> Int)

    companion object {

        /** Placeholder for the version number for a first open */
        const val FIRST_OPEN = -1
    }
}