/*
 * Copyright 2016-2017 Julien Guerinet
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

package com.guerinet.suitcase.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.support.annotation.StringRes
import android.widget.Toast
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
     * Displays a toast of short length using the app [context] and the given [message]
     */
    @JvmStatic
    fun toast(context: Context, @StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a toast of short length using the app [context] and the given [message]
     */
    @JvmStatic
    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Opens a given [url] using the app [context]
     */
    @JvmStatic
    fun openUrl(context: Context, url: String) {
        // Check that http:// or https:// is there
        val fullUrl : String
        if (!url.startsWith("http://", true) && !url.startsWith("https://", true)) {
            fullUrl = "http://" + url
        } else {
            fullUrl = url
        }

        context.startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(fullUrl)))
    }

    /**
     * Attempts to open the Pdf at the given [path] using the app [context]
     * @throws ActivityNotFoundException if no app to open the pdf is found
     */
    @JvmStatic
    @Throws(ActivityNotFoundException::class)
    fun openPdf(context: Context, path: Uri) {
        // Check if there is a Pdf reader
        val packageManager = context.packageManager
        val pdfIntent = Intent(Intent.ACTION_VIEW).setType("application/pdf")
        val list = packageManager.queryIntentActivities(pdfIntent, PackageManager.MATCH_DEFAULT_ONLY)

        if (list.isNotEmpty()) {
            // If there is one, use it
            val intent = Intent(Intent.ACTION_VIEW)
                    .setDataAndType(path, "application/pdf")
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or(Intent.FLAG_GRANT_READ_URI_PERMISSION))
            context.startActivity(intent)
        } else {
            // If not, throw the exception
            throw ActivityNotFoundException("No Pdf app found")
        }
    }

    /**
     * Opens an app using the app [context] and the app [packageName] in the Play Store or in the
     *  browser (if the Play Store is not found)
     */
    @JvmStatic
    fun openPlayStoreApp(context: Context, packageName: String = context.packageName) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +
                    packageName)))
        } catch (e: ActivityNotFoundException) {
            // If the user does not have the Play Store installed, open it in their browser
            openUrl(context, "https://play.google.com/store/app/details?id=" + packageName)
        }
    }

    /**
     * Deletes the contents of a [folder] and the folder itself if [deleteFolder] is true
     */
    @JvmStatic
    fun deleteFolderContents(folder: File, deleteFolder: Boolean = true) {
        val files = folder.listFiles()
        if (files != null) {
            for (file in files) {
                // Go through the folder's files
                if (file.isDirectory) {
                    // Recursively call this method if the file is a folder
                    deleteFolderContents(file, true)
                } else {
                    file.delete()
                }
            }
        }

        if (deleteFolder) {
            // Delete the folder if necessary
            folder.delete()
        }
    }

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

    /**
     * @param manager ConnectivityManager instance
     * @return True if the user is connected to the internet, false otherwise
     */
    @JvmStatic
    fun isConnected(manager: ConnectivityManager): Boolean {
        val networkInfo = manager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    /**
     * @param context App context
     * @return True if the user is connected to the internet, false otherwise
     */
    @JvmStatic
    fun isConnected(context: Context): Boolean {
        return isConnected(context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager)
    }

    /**
     * @return Resource Id for a given [attributeId] to set an attribute programmatically, using
     *  the app [context]
     */
    @JvmStatic
    fun getResourceFromAttributes(context: Context, attributeId: Int): Int {
        // Get the attribute in TypedArray form
        val typedArray = context.obtainStyledAttributes(intArrayOf(attributeId))

        // Extract the resource Id
        val resource = typedArray.getResourceId(0, 0)

        // Recycle the typed array
        typedArray.recycle()

        return resource
    }
}