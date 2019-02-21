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

package com.guerinet.suitcase.util.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.Uri
import android.os.LocaleList
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.guerinet.suitcase.util.Device
import java.util.Locale

/**
 * Context extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * Device screen width, in pixels
 */
val Context.displayWidth: Int
    get() = displaySize.x

/**
 * Device screen height, in pixels
 */
val Context.displayHeight: Int
    get() = displaySize.y

/**
 * Device display size as a [Point]
 */
val Context.displaySize: Point
    get() {
        val windowManager = if (this is Activity) {
            windowManager
        } else {
            getSystemService(Context.WINDOW_SERVICE) as WindowManager
        }

        return Point().apply {
            windowManager.defaultDisplay.getSize(this)
        }
    }

/**
 * Returns a [color] from the resources in a backwards-compatible manner
 */
fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

/**
 * Opens the given [url]
 */
@Throws(ActivityNotFoundException::class)
fun Context.openUrl(url: String) {
    // Check that http:// or https:// is there
    val fullUrl = if (!url.startsWith("http://", true) && !url.startsWith("https://", true)) {
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
@Throws(ActivityNotFoundException::class)
fun Context.openCustomTab(
    url: String,
    @ColorRes toolbarColor: Int? = null,
    @DrawableRes closeButtonId: Int? = null
) {
    // Check that the scheme is present, add it if not
    val fullUrl = if (!url.startsWith("http://", true) && !url.startsWith("https://", true)) {
        "http://$url"
    } else {
        url
    }

    val builder = CustomTabsIntent.Builder()
        .addDefaultShareMenuItem()

    if (toolbarColor != null) {
        // Set the custom toolbar color if there is one
        builder.setToolbarColor(getColorCompat(toolbarColor))
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
 * Returns the resource id for a given [attributeId] to set an attribute programmatically (0 if not
 *  found)
 */
fun Context.getAttributeResourceId(attributeId: Int): Int {
    // Get the attribute in types array form
    val typedArray = obtainStyledAttributes(intArrayOf(attributeId))

    // Extract the resource Id
    val resource = typedArray.getResourceId(0, 0)

    // Recycle the typed array
    typedArray.recycle()

    return resource
}

/**
 * Returns the resource Id of the given [type] for the given [id] name
 */
fun Context.getResourceId(type: String, id: String): Int =
    resources.getIdentifier(id, type, packageName)

/**
 * Returns true if the given [permission] is granted
 */
fun Context.isPermissionGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

/**
 * True if the device is connected to the internet, false otherwise
 *  This assumes the internet permission has been granted
 */
val Context.isConnected: Boolean
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isConnected

/**
 * Wraps the current context with a [language] change and returns the corresponding [ContextWrapper]
 */
fun Context.wrapWithLanguage(language: String): ContextWrapper {
    val locale = Locale(language)
    val configuration = resources.configuration

    configuration.setLocale(locale)

    if (Device.isApiLevel(24)) {
        val localeList = LocaleList(locale)
        LocaleList.setDefault(localeList)
        configuration.locales = localeList
    }

    return ContextWrapper(createConfigurationContext(configuration))
}
