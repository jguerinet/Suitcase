/*
 * Copyright 2016-2021 Julien Guerinet
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

import com.guerinet.suitcase.settings.StringSetting
import com.russhwolf.settings.Settings
import kotlinx.datetime.LocalDate

/**
 * Preferences utility class for [LocalDate]s
 * @author Julien Guerinet
 * @since 7.0.0
 */
open class LocalDateSetting(settings: Settings, key: String, defaultValue: LocalDate) :
    StringSetting(settings, key, defaultValue.toString()) {

    /**
     * Backing date property for getting and setting this pref
     */
    open var date: LocalDate
        get() = LocalDate.parse(super.value)
        set(value) {
            super.value = value.toString()
        }
}
