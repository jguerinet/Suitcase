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

package com.guerinet.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

/**
 * Logs a date/time the app was updates
 * @author Julien Guerinet
 * @since 4.4.0
 *
 * @param version   App version that the user has updated to
 * @param timestamp Date/time that the user has updated to this version, defaults to now
 * @param id        Auto-generated Id
 */
@Entity
data class AppUpdate(
    val version: String,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    @PrimaryKey(autoGenerate = true) val id: Int
) {
    companion object {

        /**
         * Returns the version String to use for the app [name] and [code]
         */
        fun getVersion(name: String, code: Int): String = "$name ($code)"
    }
}