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

package com.guerinet.suitcase.ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * EditText extensions
 * @author Julien Guerinet
 * @since 2.6.8
 */

/**
 * Adds a [TextWatcher] and calls the [watcher] whenever the text changes
 */
fun EditText.watch(watcher: (text: String?) -> Unit) =
    addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable?) = watcher(s?.toString())

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })

/**
 * Sets the IME button to be the [type] on this [EditText] and calls [onClick] when pressed
 */
fun EditText.setImeButton(type: Int, onClick: () -> Unit) {
    imeOptions = type
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == type) {
            onClick()
        }
        false
    }
}
