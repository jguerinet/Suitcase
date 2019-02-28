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
 * Abstract class that converts a Shared Pref to a LiveData. To be implemented for the different types of values
 *  Built off of: https://gist.github.com/rharter/1df1cd72ce4e9d1801bd2d49f2a96810
 * @author Julien Guerinet
 * @since 5.1.1
 */
class SharedPrefLiveData<T>(
    private val prefs: SharedPreferences,
    private val key: String,
    private val valueFromPrefs: () -> T
) : LiveData<T>() {

    private val prefChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == this.key) {
            // If the changed key was this one, update the LiveData value
            value = valueFromPrefs()
        }
    }

    override fun onActive() {
        super.onActive()
        // First value should be the current one
        value = valueFromPrefs()
        prefs.registerOnSharedPreferenceChangeListener(prefChangeListener)
    }

    override fun onInactive() {
        prefs.unregisterOnSharedPreferenceChangeListener(prefChangeListener)
        super.onInactive()
    }
}
