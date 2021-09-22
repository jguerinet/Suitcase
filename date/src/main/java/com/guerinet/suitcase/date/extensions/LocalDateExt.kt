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

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

/**
 * LocalDate extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * Returns today as a [LocalDate]
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
