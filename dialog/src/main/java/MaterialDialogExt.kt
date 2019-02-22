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

package com.guerinet.suitcase.dialog

import com.afollestad.materialdialogs.DialogCallback
import com.afollestad.materialdialogs.MaterialDialog

/**
 * MaterialDialog extensions
 * @author Julien Guerinet
 * @since 5.0.1
 */

/**
 * Adds "Ok" as a positive button, with an optional [callback] called (defaults to null)
 */
fun MaterialDialog.okButton(callback: DialogCallback? = null) = positiveButton(android.R.string.ok, click = callback)

/**
 * Adds "Cancel" as a negative button, with an optional [callback] called (defaults to null)
 */
fun MaterialDialog.cancelButton(callback: DialogCallback? = null) =
    negativeButton(android.R.string.cancel, click = callback)