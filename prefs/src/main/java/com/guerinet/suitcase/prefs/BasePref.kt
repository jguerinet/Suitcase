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

package com.guerinet.suitcase.prefs

import android.content.SharedPreferences

/**
 * Base class for all SharedPreferences helpers
 * @author Julien Guerinet
 * @since 2.0.0
 */
open class BasePref(protected val prefs:SharedPreferences, protected val key:String) {

    /**
     * Clears the [prefs] of anything stored at this [key]
     */
    open fun clear() {
        prefs.edit().remove(key).apply()
    }

    /**
     * @return True if there is something stored in these [prefs] at this [key], false otherwise
     */
    open fun isSet(): Boolean {
        return prefs.contains(key)
    }
}