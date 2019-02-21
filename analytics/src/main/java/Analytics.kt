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

package com.guerinet.suitcase.analytics

import android.app.Activity

/**
 * Generic Analytics interface. Could be implemented using Firebase in production and simple logging in testing
 * @author Julien Guerinet
 * @since 5.0.0
 */
interface Analytics {

    /**
     * Logs an event with the [name] and an optional set of [params] to attach
     */
    fun event(name: String, vararg params: Pair<String, Any?>)

    /**
     * Sets the screen [name] for the [activity]
     */
    fun screen(activity: Activity, name: String)

    /**
     * Sets a list of user [properties] on the [Analytics] instance
     */
    fun setUserProperties(vararg properties: Pair<String, String?>)
}
