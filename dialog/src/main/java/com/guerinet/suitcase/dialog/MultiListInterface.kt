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

package com.guerinet.suitcase.dialog

/**
 * Interface used for creating multiple choice list dialogs
 * @author Julien Guerinet
 * @since 2.0.0
 */
interface MultiListInterface {

    /**
     * An [IntArray] corresponding to position of the currently checked choices
     */
    val selectedItems: Array<Int>

    /**
     * List of [String] choices to display
     */
    val choices: Array<String>

    /**
     * Called when the dialog closes with the list of choice [positions] selected
     */
    fun onChoicesSelected(positions: Array<Int>)
}