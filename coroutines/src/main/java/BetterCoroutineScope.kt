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

package com.guerinet.suitcase.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

/**
 * A better version of the CoroutineScope, with job cancellation.
 *  Note: This will be part of Coroutines soon. See:
 *      https://github.com/Kotlin/kotlinx.coroutines/issues/829
 *      https://github.com/Kotlin/kotlinx.coroutines/issues/828
 * @author Julien Guerinet
 * @since 4.8.0
 */
interface BetterCoroutineScope : CoroutineScope {

    val job: Job

    /**
     * Cancels the Job
     */
    fun cancel() = job.cancel()
}
