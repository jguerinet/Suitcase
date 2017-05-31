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

package com.guerinet.suitcase.date

import android.content.SharedPreferences
import com.guerinet.suitcase.prefs.StringPref
import org.threeten.bp.ZonedDateTime

/**
 * Preferences utility class for ZonedDateTimes
 * @author Julien Guerinet
 * @since 2.0.0
 */
open class DatePref(prefs: SharedPreferences, key: String, defaultValue: ZonedDateTime?) :
        StringPref(prefs, key, defaultValue?.toString()) {

    /**
     * @return Current value stored at this [key], the [defaultValue] if none stored
     */
    open fun getDate(): ZonedDateTime? {
        // Get the stored String
        val string = super.get()

        // If the stored String is null, return null. If not, get the ZonedDateTime out of it
        return if (string == null) null else ZonedDateTime.parse(string)
    }

    /**
     * Sets the [value] at the given [key] in these [prefs]
     */
    open fun set(value: ZonedDateTime?) {
        super.set(value?.toString())
    }
}