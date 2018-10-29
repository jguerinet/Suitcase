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

import android.content.pm.PackageManager
import android.os.Build
import java.util.*

/**
 * Static utility classes
 * @author Julien Guerinet
 * @since 2.0.0
 */
object Utils {

    /**
     * @return Randomly generated UUID
     */
    @JvmStatic
    fun uuid(): String = UUID.randomUUID().toString()

    /**
     * Returns true we have been granted a permission (this is checked using the [grantResults])
     *  To be used within Activity.onRequestPermissionsResult()
     */
    fun isPermissionGranted(grantResults: IntArray): Boolean =
        grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED

    /**
     * Creates a debug String that can be attached to feedback or bug reports. The String contains
     *  the device model, sdk version, app [versionName], app [versionCode], language,
     *  and any [customInfo] passed (defaults to an empty String)
     */
    fun getDebugInfo(versionName: String, versionCode: Int, customInfo: String = ""): String =
        "===============" +
                "\nDebug Info" +
                "\n===============" +
                "\nDevice: ${Device.model()}" +
                "\nSDK Version: ${Build.VERSION.SDK_INT}" +
                "\nApp Version: $versionName" +
                "\nBuild Number: $versionCode" +
                "\nLanguage: ${Locale.getDefault().language}" +
                (if (customInfo.isNotBlank()) "\n$customInfo" else "") +
                "\n===============\n\n"

    /**
     * Returns true if the [newVersionName] is more recent than the [oldVersionName] (which is the
     *  current version if not specified)
     */
    @JvmStatic
    @JvmOverloads
    fun isNewerVersion(oldVersionName: String? = BuildConfig.VERSION_NAME, newVersionName: String?):
            Boolean {
        if (oldVersionName == null || newVersionName == null) {
            return false
        }

        // Split the version by the periods
        val oldVersion = oldVersionName.split(".")
        val newVersion = newVersionName.split(".")

        // Get the smaller number of the 2
        val maxIndex = maxOf(oldVersion.size, newVersion.size)

        for (i in 0..maxIndex) {
            try {
                val oldNumber = Integer.parseInt(oldVersion[i])
                val newNumber = Integer.parseInt(newVersion[i])

                if (newNumber > oldNumber) {
                    // New number is higher than the current number: update necessary
                    return true
                } else if (newNumber < oldNumber) {
                    // New number is lower than the current number: no update necessary
                    return false
                }
                // No number is same as old number: continue loop
            } catch (e: Exception) {
                // Error parsing the number, no visible update necessary
                return false
            }
        }

        // Old version is same as new version: no version necessary
        return false
    }
}