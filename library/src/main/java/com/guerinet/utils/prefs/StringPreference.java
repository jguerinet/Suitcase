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

import javax.inject.Inject;

/**
 * Preference utility class for Strings
 * @author Julien Guerinet
 * @since 1.0.0
 */
public class StringPreference {
    /**
     * {@link SharedPreferences} instance
     */
    protected final SharedPreferences prefs;
    /**
     * Key under which the pref should be stored
     */
    protected final String key;
    /**
     * Default value
     */
    protected final String defaultValue;

    /**
     * Default Constructor
     *
     * @param prefs         {@link SharedPreferences} instance
     * @param key           Key under which the pref should be stored
     * @param defaultValue  Default value
     */
    @Inject
    public StringPreference(@NonNull SharedPreferences prefs, @NonNull String key,
            String defaultValue) {
        this.prefs = prefs;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    /**
     * @return Current value of this String, defaulting to the default value
     */
    public String get() {
        return prefs.getString(key, defaultValue);
    }

    /**
     * Sets the value in the {@link SharedPreferences}
     *
     * @param value New value to save
     */
    public void set(String value) {
        prefs.edit()
                .putString(key, value)
                .apply();
    }

    /**
     * Clears the current {@link SharedPreferences}
     */
    public void clear() {
        prefs.edit()
                .remove(key)
                .apply();
    }
}
