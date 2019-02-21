/*
 * Copyright 2016-2019 Julien Guerinet
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
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

/**
 * ZonedDateTime extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * True if this [ZonedDateTime] is in the past, false otherwise (today would return false)
 */
val ZonedDateTime.isPast: Boolean
    get() = ZonedDateTime.now().isAfter(this)

/**
 * True if this [ZonedDateTime] is today (regardless of the time), false otherwise
 */
val ZonedDateTime.isToday: Boolean
    get() = this.toLocalDate() == LocalDate.now()

/**
 * True if this [ZonedDateTime] is in the future, false otherwise (today would return false)
 */
val ZonedDateTime.isFuture: Boolean
    get() = ZonedDateTime.now().isBefore(this)

/**
 * RFC 1123 String representation of the [ZonedDateTime] in the UTC time zone,
 *  null if the [ZonedDateTime] was null
 */
val ZonedDateTime?.rfc1123String: String?
    get() = this?.run { Format.rfc1123Formatter.format(this) }

/**
 * Allows us to use the [ZonedDateTime] as a range
 */
operator fun ZonedDateTime.rangeTo(other: ZonedDateTime) = ZonedDateProgression(this, other)

class ZonedDateIterator(
    startDate: ZonedDateTime,
    val endDateInclusive: ZonedDateTime,
    val stepDays: Long
) : Iterator<ZonedDateTime> {

    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDateInclusive

    override fun next(): ZonedDateTime {
        val next = currentDate
        currentDate = currentDate.plusDays(stepDays)
        return next
    }
}

class ZonedDateProgression(
    override val start: ZonedDateTime,
    override val endInclusive: ZonedDateTime,
    val stepDays: Long = 1
) :
    Iterable<ZonedDateTime>, ClosedRange<ZonedDateTime> {

    override fun iterator(): Iterator<ZonedDateTime> =
        ZonedDateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = ZonedDateProgression(start, endInclusive, days)
}
