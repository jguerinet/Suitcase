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

import android.content.ActivityNotFoundException
import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.widget.Toast
import com.guerinet.suitcase.util.extensions.getAttributeResourceId
import com.guerinet.suitcase.util.extensions.getResourceId
import com.guerinet.suitcase.util.extensions.isConnected
import com.guerinet.suitcase.util.extensions.openCustomTab
import com.guerinet.suitcase.util.extensions.openPdf
import com.guerinet.suitcase.util.extensions.openPlayStoreApp
import com.guerinet.suitcase.util.extensions.openUrl
import java.io.File
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
    fun uuid():String {
        return UUID.randomUUID().toString()
    }

    /**
     *  @return True if the [newVersionName] is more recent than the [oldVersionName] (which is the
     *      current version if not specified)
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

    /**
     * Deletes the contents of a [folder] and the folder itself if [deleteFolder] is true
     */
    @JvmStatic
    @JvmOverloads
    @Deprecated("Replaced with by a Kotlin method", ReplaceWith("folder.deleteRecursively()"))
    fun deleteFolder(folder: File, deleteFolder: Boolean = true) = folder.deleteRecursively()

    /**
     * @return Folder with the given [folderName] and from the given [type] from the external
     *  files directory using the app [context]. Will create it if it doesn't exist already
     */
    @JvmStatic
    fun getFolder(context: Context, folderName: String, type: String?): File {
        val folder = File(context.getExternalFilesDir(type), folderName)

        // Create it if it doesn't exist already or if it isn't a directory
        if (!folder.exists() || !folder.isDirectory) {
            folder.mkdirs()
        }

        return folder
    }

    /* DEPRECATED */

    /**
     * @param manager ConnectivityManager instance
     * @return True if the user is connected to the internet, false otherwise
     */
    @JvmStatic
    @Deprecated("Replaced by extension",
            ReplaceWith("manager.isConnected", "com.guerinet.suitcase.util.extensions.isConnected"))
    fun isConnected(manager: ConnectivityManager): Boolean = manager.isConnected

    /**
     * @param context App context
     * @return True if the user is connected to the internet, false otherwise
     */
    @JvmStatic
    @Deprecated("Replaced by extension",
            ReplaceWith("context.isConnected", "com.guerinet.suitcase.util.extensions.isConnected"))
    fun isConnected(context: Context): Boolean = context.isConnected

    /**
     * @return Resource Id for a given [attributeId] to set an attribute programmatically, using
     *  the app [context]
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith("context.getAttributeResourceId(attributeId)",
            "com.guerinet.suitcase.util.extensions.getAttributeResourceId"))
    fun getResourceFromAttributes(context: Context, attributeId: Int): Int =
            context.getAttributeResourceId(attributeId)

    /**
     * @return The resource Id of the given [type] for the given [id] name in the given [context]
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith("context.getResourceId(type, id)",
            "com.guerinet.suitcase.util.extensions.getResourceId"))
    fun getResourceId(context: Context, type: String, id: String): Int =
            context.getResourceId(type, id)

    /**
     * Displays a toast of short length using the app [context] and the given [message]
     */
    @JvmStatic
    @Deprecated("Replaced by android-ktx")
    fun toast(context: Context, @StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a toast of short length using the app [context] and the given [message]
     */
    @JvmStatic
    @Deprecated("Replaced by android-ktx")
    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Opens a given [url] using the app [context]
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith("context.openUrl(url)",
            "com.guerinet.suitcase.util.extensions.openUrl"))
    fun openUrl(context: Context, url: String) = context.openUrl(url)

    /**
     * Opens a Chrome custom tab when opening a [url] using the app [context] with a default share
     * option. Optionally sets the [toolbarColor] (it's a light grey if none supplied) and uses the
     * Drawable [closeButtonId] to set a custom close button (it's a black cross if none supplied)
     */
    @JvmStatic
    @Deprecated("Replaced by extension",
            ReplaceWith("context.openCustomTab(url, toolbarColor, closeButtonId)",
                    "com.guerinet.suitcase.util.extensions.openCustomTab"))
    fun openCustomTab(context: Context, url: String, @ColorRes toolbarColor: Int? = null,
                      @DrawableRes closeButtonId: Int? = null) =
            context.openCustomTab(url, toolbarColor, closeButtonId)

    /**
     * Attempts to open the Pdf at the given [path] using the app [context]
     * @throws ActivityNotFoundException if no app to open the pdf is found
     */
    @JvmStatic
    @Throws(ActivityNotFoundException::class)
    @Deprecated("Replaced by extension", ReplaceWith("context.openPdf(path)",
            "com.guerinet.suitcase.util.extensions.openPdf"))
    fun openPdf(context: Context, path: Uri) = context.openPdf(path)

    /**
     * Opens an app using the app [context] and the app [packageName] in the Play Store or in the
     *  browser (if the Play Store is not found)
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith("context.openPlayStoreApp(packageName)",
            "com.guerinet.suitcase.util.extensions.openPlayStoreApp"))
    fun openPlayStoreApp(context: Context, packageName: String) =
            context.openPlayStoreApp(packageName)
}