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

package com.guerinet.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guerinet.room.converter.ZonedDateTimeConverter

/**
 * Database to store all [AppUpdate]s
 * @author Julien Guerinet
 * @since 4.4.0
 */
@Database(exportSchema = false, entities = [AppUpdate::class], version = 1)
@TypeConverters(ZonedDateTimeConverter::class)
abstract class UpdateDb : RoomDatabase() {

    /**
     * Returns the [UpdateDao] instance
     */
    abstract fun updateDao(): UpdateDao

    companion object {

        /**
         * Initializes the [UpdateDb] using the app [context]
         */
        fun init(context: Context): UpdateDb =
            Room.databaseBuilder(context, UpdateDb::class.java, "update-db").build()
    }
}
