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
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import kotlinx.coroutines.flow.Flow

/**
 * Base class for all Settings helpers
 * @author Julien Guerinet
 * @since 7.0.0
 */
abstract class BaseSetting<T>(
    protected val settings: Settings,
    protected val key: String,
    protected val defaultValue: T
) {

    /** Backing property for getting and setting this pref */
    abstract var value: T

    /** True if there is something stored in these [settings] at this [key], false otherwise */
    val isSet: Boolean
        get() = settings.contains(key)

    @ExperimentalSettingsApi
    protected val observableSettings: ObservableSettings
        get() {
            if (settings !is ObservableSettings) {
                error("Settings is not an instance of ObservableSettings")
            }
            return settings
        }

    /**
     * Abstract setter, to be used when you cannot use assignments (custom one-line setters)
     */
    abstract fun set(value: T)

    /**
     * Returns a [Flow] for this setting
     */
    @ExperimentalSettingsApi
    abstract fun asFlow(): Flow<T>

    /**
     * Clears the [settings] of anything stored at this [key]
     */
    open fun clear() = settings.remove(key)
}
