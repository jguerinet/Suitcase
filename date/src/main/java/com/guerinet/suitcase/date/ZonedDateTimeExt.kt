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

package com.guerinet.suitcase.date

import org.threeten.bp.ZonedDateTime

/**
 * [ZonedDateTime] extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * Returns true if this ZonedDateTime is in the past, false otherwise (today would return false)
 */
fun ZonedDateTime.isPast(): Boolean {
    return ZonedDateTime.now().isAfter(this)
}

/**
 * Returns true if this ZonedDateTime is in the future, false otherwise (today would return false)
 */
fun ZonedDateTime.isFuture(): Boolean {
    return ZonedDateTime.now().isBefore(this)
}

/**
 * Returns the RFC 1123 String representation of the [ZonedDateTime] in the UTC time zone,
 *  null if the [ZonedDateTime] was null
 */
fun ZonedDateTime?.getRFC1123String(): String? {
    return if (this == null) null else Format.rfc1123Formatter.format(this)
}

/**
 * Allows us to use the [ZonedDateTime] as a range
 */
operator fun ZonedDateTime.rangeTo(other: ZonedDateTime) = DateProgression(this, other)

class DateIterator(startDate: ZonedDateTime, val endDateInclusive: ZonedDateTime,
                   val stepDays: Long) : Iterator<ZonedDateTime> {

    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDateInclusive

    override fun next(): ZonedDateTime {
        val next = currentDate
        currentDate = currentDate.plusDays(stepDays)
        return next
    }
}

class DateProgression(override val start: ZonedDateTime,
                      override val endInclusive: ZonedDateTime,
                      val stepDays: Long = 1) :
        Iterable<ZonedDateTime>, ClosedRange<ZonedDateTime> {

    override fun iterator(): Iterator<ZonedDateTime> =
            DateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = DateProgression(start, endInclusive, days)
}