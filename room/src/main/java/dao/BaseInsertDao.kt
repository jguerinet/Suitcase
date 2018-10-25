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

package com.guerinet.room.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Basic Dao INSERT functions for one model
 * @author Julien Guerinet
 * @since 4.2.1
 */
interface BaseInsertDao<T> {

    /**
     * Inserts 1 [obj] into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    /**
     * Inserts a list of [objects] into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(objects: List<T>)
}