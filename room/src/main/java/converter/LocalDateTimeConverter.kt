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

package com.guerinet.room.converter

import kotlinx.datetime.LocalDateTime

/**
 * Converts [LocalDateTime]s to Strings and vice-versa for Room
 * @author Julien Guerinet
 * @since 4.0.0
 */
class LocalDateTimeConverter : BaseConverter<LocalDateTime>() {

    override fun objectFromString(value: String): LocalDateTime? = try {
        LocalDateTime.parse(value)
    } catch (e: Exception) {
        null
    }
}
