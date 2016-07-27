/*
 * Copyright 2016 Julien Guerinet
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

package com.guerinet.utils.prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.threeten.bp.LocalDate;

/**
 * Preference utility class for {@link LocalDate}s
 * @author Julien Guerinet
 * @since 1.1.4
 */
public class LocalDatePreference extends StringPreference {
    /**
     * Default value
     */
    @Nullable
    protected final LocalDate defaultValue;

    /**
     * Default Constructor
     *
     * @param prefs         {@link SharedPreferences} instance
     * @param key           Key under which the pref should be stored
     * @param defaultValue  Default value
     */
    public LocalDatePreference(@NonNull SharedPreferences prefs, @NonNull String key,
            @Nullable LocalDate defaultValue) {
        super(prefs, key, defaultValue == null ? null : defaultValue.toString());
        this.defaultValue = defaultValue;
    }

    /**
     * @return Current value of this {@link LocalDate}, defaulting to the default value
     */
    public LocalDate getDate() {
        String storedDate = super.get();

        // If the stored date is null, return null
        if (storedDate == null) {
            return null;
        }

        // If not, parse it onto a ZoneDateTime
        return LocalDate.parse(storedDate);
    }

    /**
     * Sets the value in the {@link SharedPreferences}
     *
     * @param value New value to save
     */
    public void set(LocalDate value) {
        super.set(value.toString());
    }
}
