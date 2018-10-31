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

import androidx.room.Dao
import androidx.room.Query
import com.guerinet.room.dao.BaseInsertDao

/**
 * Dao to retrieve the [AppUpdate]s
 * @author Julien Guerinet
 * @since 4.4.0
 */
@Dao
interface UpdateDao : BaseInsertDao<AppUpdate> {

    /**
     * Returns all of the [AppUpdate]s
     */
    @Query("SELECT * FROM AppUpdate ORDER BY id DESC")
    fun getAll(): List<AppUpdate>
}