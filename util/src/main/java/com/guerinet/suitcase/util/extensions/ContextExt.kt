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

package com.guerinet.suitcase.util.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat

/**
 * [Context] extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * Opens the given [url]
 */
fun Context.openUrl(url: String) {
    // Check that http:// or https:// is there
    val fullUrl = if (! url.startsWith("http://", true) && ! url.startsWith("https://", true)) {
        "http://$url"
    } else {
        url
    }
    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(fullUrl)))
}

/**
 * Opens a Chrome custom tab when opening a [url] with a default share option.
 *  Optionally sets the [toolbarColor] (it's a light grey if none supplied) and uses the Drawable
 *  [closeButtonId] to set a custom close button (it's a black cross if none supplied)
 */
fun Context.openCustomTab(url: String, @ColorRes toolbarColor: Int? = null,
                          @DrawableRes closeButtonId: Int? = null) {
    // Check that the scheme is present, add it if not
    val fullUrl = if (! url.startsWith("http://", true) && ! url.startsWith("https://", true)) {
        "http://$url"
    } else {
        url
    }

    val builder = CustomTabsIntent.Builder()
            .addDefaultShareMenuItem()

    if (toolbarColor != null) {
        // Set the custom toolbar color if there is one
        builder.setToolbarColor(ContextCompat.getColor(this, toolbarColor))
    }

    if (closeButtonId != null) {
        // Set the custom close button icon if there is one
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(this.resources, closeButtonId))
    }

    // Build and launch
    builder.build().launchUrl(this, Uri.parse(fullUrl))
}

/**
 * Attempts to open the Pdf at the given [path]
 * @throws ActivityNotFoundException if no app to open the pdf is found
 */
@Throws(ActivityNotFoundException::class)
fun Context.openPdf(path: Uri) {
    // Check if there is a Pdf reader
    val packageManager = packageManager
    val pdfIntent = Intent(Intent.ACTION_VIEW).setType("application/pdf")
    val list = packageManager.queryIntentActivities(pdfIntent, PackageManager.MATCH_DEFAULT_ONLY)

    if (list.isNotEmpty()) {
        // If there is one, use it
        val intent = Intent(Intent.ACTION_VIEW)
                .setDataAndType(path, "application/pdf")
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    } else {
        // If not, throw the exception
        throw ActivityNotFoundException("No Pdf app found")
    }
}

/**
 * Opens an app by its [packageName] in the Play Store or in the browser
 *  (if the Play Store is not found)
 */
fun Context.openPlayStoreApp(packageName: String = this.packageName) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
    } catch (e: ActivityNotFoundException) {
        // If the user does not have the Play Store installed, open it in their browser
        openUrl("https://play.google.com/store/app/details?id=$packageName")
    }
}

/**
 * True if the device is connected to the internet, false otherwise
 */
val Context.isConnected: Boolean
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isConnected