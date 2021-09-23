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

package com.guerinet.suitcase.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getBooleanOrNullFlow
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow

/**
 * Helper class for nullable Boolean settings
 * @author Julien Guerinet
 * @since 7.0.0
 */
open class NullBooleanSetting(settings: Settings, key: String, defaultValue: Boolean? = null) :
    BaseSetting<Boolean?>(settings, key, defaultValue) {

    override var value: Boolean?
        get() = settings.getBooleanOrNull(key) ?: defaultValue
        set(value) = set(value)

    override fun set(value: Boolean?) {
        if (value != null) {
            settings[key] = value
        } else {
            // If the value that we are trying to set is null, clear the value
            clear()
        }
    }

    @ExperimentalSettingsApi
    override fun asFlow(): Flow<Boolean?> = observableSettings.getBooleanOrNullFlow(key)
}
