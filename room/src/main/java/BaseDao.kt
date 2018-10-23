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

package com.guerinet.room

import androidx.room.Insert
import androidx.room.Update

/**
 * Basic Dao functions for one model
 * @author Julien Guerinet
 * @since 4.2.0
 */
interface BaseDao<T> {

    /**
     * Inserts 1 [obj] into the database
     */
    @Insert
    fun insert(obj: T)

    /**
     * Inserts a list of [objects] into the database
     */
    @Insert
    fun insert(objects: List<T>)

    /**
     * Updates the [obj]
     */
    @Update
    fun update(obj: T)
}