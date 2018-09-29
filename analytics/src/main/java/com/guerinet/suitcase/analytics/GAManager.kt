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
import android.content.Context
import android.util.Log
import androidx.annotation.XmlRes
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker

/**
 * Sends screens and events to Google Analytics
 * @author Julien Guerinet
 * @since 2.1.0
 *
 * @param context  App context
 * @param config   Xml file used to configure the tracker
 */
open class GAManager(context: Context, @XmlRes config: Int) {

    @SuppressLint("MissingPermission")
    private val tracker: Tracker = GoogleAnalytics.getInstance(context).newTracker(config)

    /**
     * True if we should not send anything to Google Analytics, false otherwise.
     *  Should be overridden by the clients if it does need to be disabled
     */
    open val isDisabled: Boolean = false

    /**
     * Sends an event with the given [category], [action], and optional [label] to Google Analytics
     */
    @JvmOverloads
    fun sendEvent(category: String, action: String, label: String? = null) {
        if (isDisabled) {
            Log.d("GAManager", "Event: $category, $action, $label")
            return
        }

        val builder = HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)

        label?.apply { builder.setLabel(this) }

        tracker.send(builder.build())

        Log.i("GAManager", "Event: $category, $action, $label")
    }

    /**
     * Sends a screen with the given [name] to Google Analytics
     */
    fun sendScreen(name: String) {
        if (isDisabled) {
            Log.d("GAManager", "Screen: $name")
            return
        }

        tracker.setScreenName(name)
        tracker.send(HitBuilders.ScreenViewBuilder().build())

        Log.i("GAManager", "Screen: $name")
    }
}