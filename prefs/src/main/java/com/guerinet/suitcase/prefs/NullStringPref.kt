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

/**
 * SharedPreferences utility class for nullable Strings
 * @author Julien Guerinet
 * @since 2.0.0
 */
open class NullStringPref(prefs: SharedPreferences, key: String, defaultValue: String?) :
    BasePref<String?>(prefs, key, defaultValue) {

    override var value: String?
        get() = prefs.getString(key, defaultValue)
        set(value) = prefs.edit().putString(key, value).apply()
}
