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

package com.guerinet.suitcase.date

import android.content.SharedPreferences
import com.guerinet.suitcase.prefs.NullStringPref
import org.threeten.bp.ZonedDateTime

/**
 * Preferences utility class for ZonedDateTimes
 * @author Julien Guerinet
 * @since 2.0.0
 */
open class NullDatePref(prefs: SharedPreferences, key: String, defaultValue: ZonedDateTime?) :
        NullStringPref(prefs, key, defaultValue?.toString()) {

    open var date: ZonedDateTime?
        get() = super.value?.run { ZonedDateTime.parse(this) }
        set(value) {
            super.value = value?.toString()
        }

    @Deprecated("Replaced by property", ReplaceWith("date"))
    open fun getZonedDateTime(): ZonedDateTime? = date

    @Deprecated("Replaced by property", ReplaceWith("date = value"))
    open fun set(value: ZonedDateTime?) {
        date = value
    }
}