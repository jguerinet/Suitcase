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

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.LocaleList
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.guerinet.suitcase.util.Device
import java.io.File
import java.util.*

/**
 * Context extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * Returns a [color] from the resources in a backwards-compatible manner
 */
fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

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
 * Returns a file (or folder if [isFolder] is true) with the given [name] and [type] (null if it
 *  does not have a specific type, defaults to null).
 *  This will create the file/folder if it doesn't exist already.
 */
fun Context.getFile(isFolder: Boolean, name: String, type: String? = null): File {
    val file = File(getExternalFilesDir(type), name)

    if (!isFolder && !file.exists()) {
        // If it's supposed to be a file and it doesn't exist, create it
        file.createNewFile()
    } else if (isFolder && (!file.exists() || !file.isDirectory)) {
        // If it's supposed to be a folder and it doesn't exist or isn't a folder, create it
        file.mkdirs()
    }
    return file
}

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

    if (Device.isAtLeastNougat()) {
        val localeList = LocaleList(locale)
        LocaleList.setDefault(localeList)
        configuration.locales = localeList
    }

    return ContextWrapper(createConfigurationContext(configuration))
}

/**
 * Creates a debug String that can be attached to feedback or bug reports. The String contains
 *  the device model, sdk version, app [versionName], app [versionCode], language, connection type,
 *  and any [customInfo] passed (defaults to an empty String)
 */
fun Context.getDebugInfo(versionName: String, versionCode: Int, customInfo: String = ""): String {
    @SuppressLint("MissingPermission")
    val info = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo

    val connection = if (info != null) {
        "${info.typeName} ${info.subtypeName}"
    } else {
        "N/A"
    }

    return "===============" +
            "\nDebug Info" +
            "\n===============" +
            "\nDevice: ${Device.model()}" +
            "\nSDK Version: ${Build.VERSION.SDK_INT}" +
            "\nApp Version: $versionName" +
            "\nBuild Number: $versionCode" +
            "\nLanguage: ${Locale.getDefault().language}" +
            "\nConnection Type: $connection" +
            (if (customInfo.isNotBlank()) "\n$customInfo" else "") +
            "\n===============\n\n"
}