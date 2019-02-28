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

package com.guerinet.suitcase.lifecycle

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

/**
 * Converts the Pref classes into LiveData observables
 *  Heavily inspired by https://gist.github.com/rharter/1df1cd72ce4e9d1801bd2d49f2a96810
 * @author Julien Guerinet
 * @since 5.1.0
 */

fun SharedPreferences.intLiveData(key: String, defValue: Int): SharedPrefLiveData<Int> {
    return SharedPrefIntLiveData(this, key, defValue)
}

fun SharedPreferences.stringLiveData(key: String, defValue: String): SharedPrefLiveData<String> {
    return SharedPrefStringLiveData(this, key, defValue)
}

fun SharedPreferences.booleanLiveData(key: String, defValue: Boolean): SharedPrefLiveData<Boolean> {
    return SharedPrefBooleanLiveData(this, key, defValue)
}

class SharedPrefIntLiveData(sharedPrefs: SharedPreferences, key: String, defValue: Int) :
    SharedPrefLiveData<Int>(sharedPrefs, key, defValue) {

    override fun getValueFromPreferences(key: String, defValue: Int): Int = sharedPrefs.getInt(key, defValue)
}

class SharedPrefStringLiveData(sharedPrefs: SharedPreferences, key: String, defValue: String) :
    SharedPrefLiveData<String>(sharedPrefs, key, defValue) {

    override fun getValueFromPreferences(key: String, defValue: String): String = sharedPrefs.getString(key, defValue)
}

/**
 * Observes a Boolean Pref
 */
class SharedPrefBooleanLiveData(sharedPrefs: SharedPreferences, key: String, defValue: Boolean) :
    SharedPrefLiveData<Boolean>(sharedPrefs, key, defValue) {

    override fun getValueFromPreferences(key: String, defValue: Boolean): Boolean =
        sharedPrefs.getBoolean(key, defValue)
}

/**
 * Abstract class that converts a Shared Pref to a LiveData. To be implemented for the different types of valies
 */
abstract class SharedPrefLiveData<T>(val sharedPrefs: SharedPreferences, val key: String, val defValue: T) :
    LiveData<T>() {

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == this.key) {
            value = getValueFromPreferences(key, defValue)
        }
    }

    abstract fun getValueFromPreferences(key: String, defValue: T): T

    override fun onActive() {
        super.onActive()
        value = getValueFromPreferences(key, defValue)
        sharedPrefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onInactive() {
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        super.onInactive()
    }
}