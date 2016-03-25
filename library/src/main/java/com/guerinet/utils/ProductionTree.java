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

package com.guerinet.utils;

import android.util.Log;

import timber.log.Timber;

/**
 * Timber tree that is used for crash reporting in production
 * @author Julien Guerinet
 * @since 1.0.0
 */
public abstract class ProductionTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        // We don't want to log verbose and debug logs
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }

        log(message);
        // If there's a crash and it's not a socket timeout, log it too
        if (t != null) {
            logException(t);
        }
    }

    /**
     * Called when there is a message to log
     *
     * @param message The message to log
     */
    protected abstract void log(String message);

    /**
     * Called when there is an exception to log
     *
     * @param t The {@link Throwable} to log
     */
    protected abstract void logException(Throwable t);
}
