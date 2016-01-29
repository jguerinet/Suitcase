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
 * Base class for all Preference helpers
 * @author Julien Guerinet
 * @since 1.0.9
 */
public class BasePreference {
    /**
     * {@link SharedPreferences} instance
     */
    protected final SharedPreferences prefs;
    /**
     * Key under which the pref should be stored
     */
    protected final String key;

    /**
     * Default Constructor
     *
     * @param prefs         {@link SharedPreferences} instance
     * @param key           Key under which the pref should be stored
     */
    @Inject
    public BasePreference(@NonNull SharedPreferences prefs, @NonNull String key) {
        this.prefs = prefs;
        this.key = key;
    }

    /**
     * Clears the current {@link SharedPreferences}
     */
    public void clear() {
        prefs.edit()
                .remove(key)
                .apply();
    }

    /**
     * @return True if the current preference is set, false otherwise
     */
    public boolean isSet() {
        return prefs.contains(key);
    }
}
