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

@file:JvmName("DateUtils")

package com.guerinet.suitcase.date

import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import org.threeten.bp.temporal.Temporal
import java.util.*

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

/**
 * @return RFC 1123 String representation of the [ZonedDateTime] in the UTC time zone,
 *  null if the [ZonedDateTime] was null
 */
fun ZonedDateTime?.getRFC1123String(): String? {
    return if (this == null) null else Format.rfc1123Formatter.format(this)
}

/**
 * @return Localized short date String (ex: 01/01/00) of the [Temporal],
 *  null if the [Temporal] was null
 */
fun Temporal?.getShortDateString(): String? {
    return if (this == null) null else Format.shortDateFormatter.format(this)
}

/**
 * @return Localized medium date String (ex: Jan 1, 2000) of the [Temporal],
 *  null if the [Temporal] was null
 */
fun Temporal?.getMediumDateString(): String? {
    return if (this == null) null else Format.mediumDateFormatter.format(this)
}

/**
 * @return Localized long date String (ex: January 1, 2000) of the [Temporal],
 *  null if the [Temporal] was null
 */
fun Temporal?.getLongDateString(): String? {
    return if (this == null) null else Format.longDateFormatter.format(this)
}

/**
 * @return Localized full date String (ex: Monday, January 1, 2000) of the [Temporal],
 *  null if the [Temporal] was null
 */
fun Temporal?.getFullDateString(): String? {
    return if (this == null) null else Format.fullDateFormatter.format(this)
}

/**
 * @return Localized short time String (ex: 3:30PM) of the [Temporal],
 *  null if the [Temporal] was null
 */
fun Temporal?.getShortTimeString(): String? {
    return if (this == null) null else Format.shortTimeFormatter.format(this)
}

object Format {

    internal val rfc1123Formatter: DateTimeFormatter by lazy {
        // We want the local to always be US regardless of the user's locale
        DateTimeFormatter.RFC_1123_DATE_TIME.withLocale(Locale.US)
    }

    internal val shortDateFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    }

    internal val mediumDateFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    }

    internal val longDateFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
    }

    internal val fullDateFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
    }

    internal val shortTimeFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
    }
}