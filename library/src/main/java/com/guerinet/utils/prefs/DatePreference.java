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

import org.threeten.bp.ZonedDateTime;

/**
 * Preference utility class for dates
 * @author Julien Guerinet
 * @since 1.0.12
 */
public class DatePreference extends StringPreference {
    /**
     * Default value
     */
    protected final ZonedDateTime defaultValue;

    /**
     * Default Constructor
     *
     * @param prefs         {@link SharedPreferences} instance
     * @param key           Key under which the pref should be stored
     * @param defaultValue  Default value
     */
    public DatePreference(@NonNull SharedPreferences prefs, @NonNull String key,
            ZonedDateTime defaultValue) {
        super(prefs, key, defaultValue == null ? null : defaultValue.toString());
        this.defaultValue = defaultValue;
    }

    /**
     * @return Current value of this date, defaulting to the default value
     */
    public ZonedDateTime getDate() {
        String storedDate = super.get();

        // If the stored date is null, return null
        if (storedDate == null) {
            return null;
        }

        // If not, parse it onto a ZoneDateTime
        return ZonedDateTime.parse(storedDate);
    }

    /**
     * Sets the value in the {@link SharedPreferences}
     *
     * @param value New value to save
     */
    public void set(ZonedDateTime value) {
        super.set(value.toString());
    }
}
