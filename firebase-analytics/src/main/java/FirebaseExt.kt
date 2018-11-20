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

import android.app.Activity
import android.content.Context
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Firebase-related extensions
 * @author Julien Guerinet
 * @since 4.6.0
 */

/**
 * Lazily loads Firebase for any [Context]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Context.firebase() = lazy { FirebaseAnalytics.getInstance(this) }

/**
 * Logs the an event with the [name] and an optional set of [params] to attach
 */
@Suppress("NOTHING_TO_INLINE")
inline fun FirebaseAnalytics.event(name: String, vararg params: Pair<String, Any?>) = logEvent(name, bundleOf(*params))

/**
 * Sets the screen [name] for the [activity]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun FirebaseAnalytics.screen(activity: Activity, name: String) = setCurrentScreen(activity, name, null)

/**
 * Sets a list of user [properties] on this [FirebaseAnalytics] instance
 */
@Suppress("NOTHING_TO_INLINE")
inline fun FirebaseAnalytics.setUserProperties(vararg properties: Pair<String, String?>) =
    properties.forEach { setUserProperty(it.first, it.second) }