/*
 * Copyright 2016-2018 Julien Guerinet
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

package com.guerinet.suitcase.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Extensions / top level functions used for Kotlin Coroutines
 * @author Julien Guerinet
 * @since 4.7.0
 */

/** Convenience variable to get the Main Dispatcher */
val uiDispatcher: CoroutineDispatcher = Dispatchers.Main

/** Convenience variable to get the Default (i.e. background) Dispatcher */
val bgDispatcher: CoroutineDispatcher = Dispatchers.Default

/** Convenience variable to get the IO (i.e. background) Dispatcher */
val ioDispatcher: CoroutineDispatcher = Dispatchers.IO