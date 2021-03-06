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

import timber.log.Timber

/**
 * Adds a tag to all Timber logs if this interface is implemented
 * @author Julien Guerinet
 * @since 4.9.0
 */
interface TimberTag {

    /** Tag to use for all of the logs coming from the class that implements this */
    val tag: String

    /** Timber instance to use to log with the [tag] */
    val timber: Timber.Tree
        get() = Timber.tag(tag)
}
