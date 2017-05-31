/*
 * Copyright 2016-2017 Julien Guerinet
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

package com.guerinet.suitcase.db

import android.text.TextUtils
import com.raizlabs.android.dbflow.converter.TypeConverter
import org.threeten.bp.LocalDate

/**
 * Converts LocalDates to Strings and vice-versa for DBFlow
 * @author Julien Guerinet
 * @since 2.0.0
 */
@com.raizlabs.android.dbflow.annotation.TypeConverter
open class LocalDateConverter() : TypeConverter<String, LocalDate>() {

    override fun getDBValue(model: LocalDate?): String? {
        return model?.toString()
    }

    override fun getModelValue(data: String?): LocalDate? {
        if (TextUtils.isEmpty(data)) {
            return null
        }
        return LocalDate.parse(data)
    }

}