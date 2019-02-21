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
import androidx.core.app.ActivityCompat

/**
 * Activity extensions
 * @author Julien Guerinet
 * @since 2.4.0
 */

/**
 * Checks if we have the given [permission], and requests it using the [requestCode] if not. Returns
 *  true if we already had the permission, false otherwise
 */
fun Activity.hasPermission(permission: String, requestCode: Int): Boolean {
    if (!isPermissionGranted(permission)) {
        // Request the permission if it's not granted yet
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        return false
    }
    // If we already have the permission, return true
    return true
}
