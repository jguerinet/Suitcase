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
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toLocalDateTime
import java.util.Locale

/**
 * ZonedDateTime extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

fun Instant.toLocalDate(): LocalDate = this.toLocalDateTime(TimeZone.currentSystemDefault()).date

/**
 * True if this [Instant] is in the past, false otherwise (today would return false)
 */
val Instant.isPast: Boolean
    get() = Clock.System.now() > this

/**
 * True if this [Instant] is today (regardless of the time), false otherwise
 */
val Instant.isToday: Boolean
    get() = this.toLocalDate() == LocalDate.today

/**
 * True if this [Instant] is in the future, false otherwise (today would return false)
 */
val Instant.isFuture: Boolean
    get() = Clock.System.now() < this

/**
 * RFC 1123 String representation of the [Instant] in the UTC time zone,
 *  null if the [Instant] was null. Note: Uses Java Time
 */
val Instant?.rfc1123String: String?
    get() = this?.run { Format.rfc1123Formatter.format(this.toJavaInstant()) }

/**
 * Returns localized short date String (ex: 01/01/00) of the [Instant],
 *  null if the [Instant] was null. Note: Uses Java Time
 */
fun Instant?.getShortDateString(locale: Locale = Locale.getDefault()): String? =
    this?.toLocalDate().getShortDateString(locale)

/**
 * Returns localized medium date String (ex: Jan 1, 2000) of the [Instant],
 *  null if the [Instant] was null. Note: Uses Java Time
 */
fun Instant?.getMediumDateString(locale: Locale = Locale.getDefault()): String? =
    this?.toLocalDate().getMediumDateString(locale)

/**
 * Returns localized long date String (ex: January 1, 2000) of the [Instant],
 *  null if the [Instant] was null. Note: Uses Java Time
 */
fun Instant?.getLongDateString(locale: Locale = Locale.getDefault()): String? =
    this?.toLocalDate().getLongDateString(locale)

/**
 * Returns localized full date String (ex: Monday, January 1, 2000) of the [Instant],
 *  null if the [Instant] was null. Note: Uses Java Time
 */
fun Instant?.getFullDateString(locale: Locale = Locale.getDefault()): String? =
    this?.toLocalDate().getFullDateString(locale)

/**
 * Returns localized short time String (ex: 3:30PM) of the [Instant],
 *  null if the [Instant] was null. Note: Uses Java Time
 */
fun Instant?.getShortTimeString(locale: Locale = Locale.getDefault()): String? =
    this?.run { Format.shortTimeFormatter.withLocale(locale).format(this.toJavaInstant()) }
