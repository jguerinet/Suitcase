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

package com.guerinet.suitcase.prefs

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

/**
 * Base class for all SharedPreferences helpers
 * @author Julien Guerinet
 * @since 2.0.0
 */
abstract class BasePref<T>(
    protected val prefs: SharedPreferences,
    protected val key: String,
    protected val defaultValue: T
) {

    /** Backing property for getting and setting this pref */
    abstract var value: T

    /** True if there is something stored in these [prefs] at this [key], false otherwise */
    val isSet: Boolean
        get() = prefs.contains(key)

    /**
     * Clears the [prefs] of anything stored at this [key]
     */
    open fun clear() = prefs.edit().remove(key).apply()

    fun liveData() = SharedPrefLiveData()

    /**
     * Abstract class that converts a Shared Pref to a LiveData. To be implemented for the different types of valies
     */
    inner class SharedPrefLiveData : LiveData<T>() {

        private val prefChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == this@BasePref.key) {
                // If the changed key was this one, update the LiveData value
                value = this@BasePref.value
            }
        }

        override fun onActive() {
            super.onActive()
            // First value should be the current one
            value = this@BasePref.value
            prefs.registerOnSharedPreferenceChangeListener(prefChangeListener)
        }

        override fun onInactive() {
            prefs.unregisterOnSharedPreferenceChangeListener(prefChangeListener)
            super.onInactive()
        }
    }
}
