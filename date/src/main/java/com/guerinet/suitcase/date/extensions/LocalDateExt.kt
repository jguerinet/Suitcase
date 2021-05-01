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

package com.guerinet.suitcase.date.extensions

import com.guerinet.suitcase.date.Format
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.todayAt
import java.util.Locale

/**
 * LocalDate extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

val LocalDate.Companion.today: LocalDate
    get() = Clock.System.todayAt(TimeZone.currentSystemDefault())

/**
 * True if this [LocalDate] is in the past, false otherwise (today would return false)
 */
val LocalDate.isPast: Boolean
    get() = LocalDate.today > this

/**
 * True if this [LocalDate] is today, false otherwise
 */
val LocalDate.isToday: Boolean
    get() = LocalDate.today == this

/**
 * True if this [LocalDate] is in the future, false otherwise (today would return false)
 */
val LocalDate.isFuture: Boolean
    get() = LocalDate.today < this

/**
 * Returns localized short date String (ex: 01/01/00) of the [LocalDate],
 *  null if the [LocalDate] was null. Note: Uses Java Time
 */
fun LocalDate?.getShortDateString(locale: Locale = Locale.getDefault()): String? =
    this?.run { Format.shortDateFormatter.withLocale(locale).format(this.toJavaLocalDate()) }

/**
 * Returns localized medium date String (ex: Jan 1, 2000) of the [LocalDate],
 *  null if the [LocalDate] was null. Note: Uses Java Time
 */
fun LocalDate?.getMediumDateString(locale: Locale = Locale.getDefault()): String? =
    this?.run { Format.mediumDateFormatter.withLocale(locale).format(this.toJavaLocalDate()) }

/**
 * Returns localized long date String (ex: January 1, 2000) of the [LocalDate],
 *  null if the [LocalDate] was null. Note: Uses Java Time
 */
fun LocalDate?.getLongDateString(locale: Locale = Locale.getDefault()): String? =
    this?.run { Format.longDateFormatter.withLocale(locale).format(this.toJavaLocalDate()) }

/**
 * Returns localized full date String (ex: Monday, January 1, 2000) of the [LocalDate],
 *  null if the [LocalDate] was null. Note: Uses Java Time
 */
fun LocalDate?.getFullDateString(locale: Locale = Locale.getDefault()): String? =
    this?.run { Format.fullDateFormatter.withLocale(locale).format(this.toJavaLocalDate()) }
