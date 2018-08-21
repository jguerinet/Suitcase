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

package com.guerinet.suitcase.analytics

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Sends screens and events to Firebase Analytics
 * @author Ali Avci
 * @since 2.6.11
 *
 * @param context  App context
 */
open class FirebaseManager(val context: Context) {

    @SuppressLint("MissingPermission")
    private val firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    /**
     * True if we should not send anything to Firebase Analytics, false otherwise.
     *  Should be overridden by the clients if it does need to be disabled
     */
    open val isDisabled: Boolean = false

    /**
     * Sends an event with the given [param], [event], and optional [label] to Firebase Analytics
     */
    @JvmOverloads
    fun sendEvent(category: String, action: String, label: String? = null) {
        if (isDisabled) {
            Log.d("GAManager", "Event: $category, $action, $label")
            return
        }

        val bundle = Bundle().apply {
            putString("category", category)
            putString("action", action)
            putString("label", label)
        }

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle)

        Log.i("GAManager", "Event: $category, $action, $label")
    }

    /**
     * Sends a screen with the given [name] to Firebase Analytics
     */
    fun sendScreen(activity: Activity, screenName: String) {
        if (isDisabled) {
            Log.d("FirebaseManager", "Screen: $screenName")
            return
        }

        firebaseAnalytics.setCurrentScreen(activity, screenName, null /* class override */)

        Log.i("FirebaseManager", "Screen: $screenName")
    }
}