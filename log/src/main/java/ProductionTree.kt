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

package com.guerinet.suitcase.log

import android.util.Log
import timber.log.Timber

/**
 * Timber tree used for crash reporting in production
 * @author Julien Guerinet
 * @since 2.0.0
 */
abstract class ProductionTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean = priority >= Log.INFO

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // We don't want to log verbose and debug logs. I'm not sure we need this anymore since we have isLoggable()
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        log(priority, tag, message)

        if (t != null) {
            // Log the throwable if there is one
            logException(t)
        }
    }

    /**
     * Called when there is a [priority], [tag] and a [message] to log
     */
    protected abstract fun log(priority: Int, tag: String?, message: String)

    /**
     * Called when there is a [Throwable] [t] to log
     */
    protected abstract fun logException(t: Throwable)
}
