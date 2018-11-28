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

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * A MainScope that binds itself to an LifecycleOwner's lifecycle
 * @author Julien Guerinet
 * @since 4.7.0
 */
class LifecycleMainScope : MainScope(), LifecycleObserver {

    /**
     * Binds this to a [lifecycleOwner]. Should be called in onCreate()
     */
    fun bind(lifecycleOwner: LifecycleOwner) = lifecycleOwner.lifecycle.addObserver(this)

    /**
     * Destroy's the [job] when the activity's onDestroy() is called
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() = cancel()
}