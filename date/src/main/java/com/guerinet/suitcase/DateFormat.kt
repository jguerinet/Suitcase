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

package com.guerinet.suitcase

import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import org.threeten.bp.temporal.Temporal
import java.util.*

/**
 * Static date utility classes
 * @author Julien Guerinet
 * @since 1.0.0
 */

object DateFormat {

    val rfc1123Formatter: DateTimeFormatter by lazy {
        // We want the local to always be US regardless of the user's locale
        DateTimeFormatter.RFC_1123_DATE_TIME.withLocale(Locale.US)
    }

    val shortDateFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    }

    val mediumDateFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    }

    val longDateFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
    }

    val shortTimeFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
    }

    /**
     * @return RFC 1123 String representation of the [dateTime] in the UTC time zone,
     *  null if the [dateTime] was null
     */
    fun getRFC1123String(dateTime: ZonedDateTime?): String? {
        return if (dateTime == null) null else
            rfc1123Formatter.format(dateTime.withZoneSameInstant(ZoneOffset.UTC))
    }

    /**
     * @return Localized short date String (ex: 01/01/00) of the given [temporal],
     *  null if the [temporal] was null
     */
    fun getShortDateString(temporal: Temporal?): String? {
        return if (temporal == null) null else shortDateFormatter.format(temporal)
    }

    /**
     * @return Localized medium date String (ex: Jan 1, 2000) of the given [temporal],
     *  null if the [temporal] was null
     */
    fun getMediumDateString(temporal: Temporal?): String? {
        return if (temporal == null) null else mediumDateFormatter.format(temporal)
    }

    /**
     * @return Localized long date String (ex: January 1, 2000) of the given [temporal],
     *  null if the [temporal] was null
     */
    fun getLongDateString(temporal: Temporal?): String? {
        return if (temporal == null) null else longDateFormatter.format(temporal)
    }

    /**
     * @return Localized short time String (ex: 3:30PM) of the given [temporal],
     *  null if the [temporal] was null
     */
    fun getShortTimeString(temporal: Temporal?): String? {
        return if (temporal == null) null else shortTimeFormatter.format(temporal)
    }
}