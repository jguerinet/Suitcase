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

package com.guerinet.suitcase.date.extensions

import org.threeten.bp.LocalDate

/**
 * LocalDate extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * True if this [LocalDate] is in the past, false otherwise (today would return false)
 */
val LocalDate.isPast: Boolean
    get() = LocalDate.now().isAfter(this)

/**
 * True if this [LocalDate] is today, false otherwise
 */
val LocalDate.isToday: Boolean
    get() = LocalDate.now() == this

/**
 * True if this [LocalDate] is in the future, false otherwise (today would return false)
 */
val LocalDate.isFuture: Boolean
    get() = LocalDate.now().isBefore(this)

/**
 * Allows us to use the [LocalDate] as a range
 */
operator fun LocalDate.rangeTo(other: LocalDate) = LocalDateProgression(this, other)

class LocalDateIterator(
    startDate: LocalDate, val endDateInclusive: LocalDate,
    val stepDays: Long
) : Iterator<LocalDate> {

    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDateInclusive

    override fun next(): LocalDate {
        val next = currentDate
        currentDate = currentDate.plusDays(stepDays)
        return next
    }
}

class LocalDateProgression(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    val stepDays: Long = 1
) :
    Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        LocalDateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = LocalDateProgression(start, endInclusive, days)
}