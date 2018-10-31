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

package com.guerinet.room.converter

import androidx.room.TypeConverter

/**
 * Extensible Room converter for MutableLists
 * @author Julien Guerinet
 * @since 4.4.1
 */
abstract class BaseMutableListConverter<T>(private val delimiter: String = ",") {

    /**
     * Default implementation of [toString] is to call [toString] on each objects and join them with
     *  the [delimiter]. This can be overridden
     */
    @TypeConverter
    open fun toString(value: MutableList<T>?): String? =
        value?.joinToString(delimiter) { objectToString(it) }

    /**
     * Default implementation of [fromString] is to split the [value] by the [delimiter] and call
     *  [objectFromString] on each value. This can be overridden
     */
    @TypeConverter
    open fun fromString(value: String?): MutableList<T>? = if (value.isNullOrEmpty()) null else
        value.split(delimiter).map { objectFromString(it) }.toMutableList()

    /**
     * Converts the [value] to a String. The default implementation is to call [toString]
     */
    open fun objectToString(value: T) = value.toString()

    /**
     * Converts the [value] into a [T] instance
     */
    abstract fun objectFromString(value: String): T
}