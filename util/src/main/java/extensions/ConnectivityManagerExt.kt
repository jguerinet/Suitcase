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

import android.annotation.SuppressLint
import android.net.ConnectivityManager

/**
 * ConnectivityManager extensions
 * @author Julien Guerinet
 * @since 2.4.0
 */

/**
 * True if the device is connected to the internet, false otherwise
 *  This assumes we have the internet permission
 */
val ConnectivityManager.isConnected: Boolean
    @SuppressLint("MissingPermission")
    get() = activeNetworkInfo?.isConnected ?: false
