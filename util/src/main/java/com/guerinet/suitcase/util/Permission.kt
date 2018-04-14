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

import android.app.Activity
import android.content.Context
import com.guerinet.suitcase.util.extensions.hasPermission
import com.guerinet.suitcase.util.extensions.isPermissionGranted

/**
 * Static Marshmallow permission helper methods
 * @author Julien Guerinet
 * @since 2.0.0
 */
@Deprecated("All methods have been moved")
object Permission {

    /**
     * @return true if the request [permission] is granted (checked with the app [context])
     */
    @JvmStatic
    @Deprecated("Replaced with extension", ReplaceWith("context.isPermissionGranted(permission)",
            "com.guerinet.suitcase.util.extensions.isPermissionGranted"))
    fun isGranted(context: Context, permission: String): Boolean =
            context.isPermissionGranted(permission)

    /**
     * Checks if the given [permission] has been granted, and has been granted using the
     *  [requestCode] and [activity] if it hasn't
     *
     *  @return True if the permission had already been granted, false otherwise
     */
    @JvmStatic
    @Deprecated("Replaced with extension",
            ReplaceWith("activity.hasPermission(permission, requestCode)",
                    "com.guerinet.suitcase.util.extensions.hasPermission"))
    fun request(activity: Activity, permission: String, requestCode: Int) =
            activity.hasPermission(permission, requestCode)

    /**
     * Checks if the [grantResults] notify us that the permission has been granted.
     *  To be used within Activity.onRequestPermissionsResult()
     * @return True if the permission has been granted
     */
    @Deprecated("Moved to Utils", ReplaceWith("Utils.isPermissionGranted(grantResults)"))
    fun isGranted(grantResults: IntArray): Boolean = Utils.isPermissionGranted(grantResults)
}