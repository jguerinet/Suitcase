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

package com.guerinet.suitcase.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * Static Marshmallow permission helper methods
 * @author Julien Guerinet
 * @since 2.0.0
 */
object Permission {

    /**
     * @return true if the request [permission] is granted (checked with the app [context])
     */
    @JvmStatic
    fun isGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_GRANTED
    }

    /**
     * Checks if the given [permission] has been granted, and has been granted using the
     *  [requestCode] and [activity] if it hasn't
     *
     *  @return True if the permission had already been granted, false otherwise
     */
    @JvmStatic
    fun request(activity: Activity, permission: String, requestCode: Int): Boolean {
        if (!isGranted(activity, permission)) {
            // Request the permission
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
            return false
        }
        // If we already have the permission, return true
        return true
    }
}