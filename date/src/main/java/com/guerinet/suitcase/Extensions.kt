/*
 * Copyright 2017 Julien Guerinet
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

@file:JvmName("DateUtils")

package com.guerinet.suitcase

import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

/**
 * Date utility extensions
 * @author Julien Guerinet
 * @since 1.0.0
 */

/**
 * @return True if this LocalDate is in the past, false otherwise (today would return false)
 */
fun LocalDate.isPast(): Boolean {
    return LocalDate.now().isAfter(this)
}

/**
 * @return True if this ZonedDateTime is in the past, false otherwise (today would return false)
 */
fun ZonedDateTime.isPast(): Boolean {
    return ZonedDateTime.now().isAfter(this)
}

/**
 * @return True if this LocalDate is in the future, false otherwise (today would return false)
 */
fun LocalDate.isFuture(): Boolean {
    return LocalDate.now().isBefore(this)
}

/**
 * @return True if this ZonedDateTime is in the future, false otherwise (today would return false)
 */
fun ZonedDateTime.isFuture(): Boolean {
    return ZonedDateTime.now().isBefore(this)
}