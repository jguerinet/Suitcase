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

import android.content.Context
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.DialogCallback
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.afollestad.materialdialogs.list.listItemsSingleChoice

/**
 * Context extensions relating to dialogs
 * @author Julien Guerinet
 * @since 2.5.0
 */

/* DIALOGS */

/**
 * Displays and returns a dialog with a [title] and a [message]. Takes an [init] block to add any other buttons
 */
fun Context.showDialog(
    @StringRes title: Int = 0,
    @StringRes message: Int = 0,
    init: (MaterialDialog.() -> Unit)? = null
) = MaterialDialog(this).show {
    build(title, message)
    init?.invoke(this)
}

/**
 * Displays and returns a dialog with a [title] and a [message]. Takes an [init] block to add any other buttons
 */
fun Context.showDialog(
    @StringRes title: Int = 0,
    message: String? = null,
    init: (MaterialDialog.() -> Unit)? = null
) = MaterialDialog(this).show {
    build(title, message)
    init?.invoke(this)
}

/* NEUTRAL */

/**
 * Displays and returns a dialog with one button. This dialog can have a [title], a [message],
 *  a [button] text (defaults to the Android OK), and a button [listener].
 */
fun Context.neutralDialog(
    @StringRes title: Int = 0,
    @StringRes message: Int = 0,
    @StringRes button: Int = android.R.string.ok,
    listener: DialogCallback? = null
) = showDialog(title, message) {
    positiveButton(button, click = listener)
}

/**
 * Displays and returns a dialog with one button. This dialog can have a [title], a [message],
 *  a [button] text (defaults to the Android OK), and a button [listener]
 */
fun Context.neutralDialog(
    @StringRes title: Int = 0,
    message: String? = null,
    @StringRes button: Int = android.R.string.ok,
    listener: DialogCallback? = null
) = showDialog(title, message) {
    positiveButton(button, click = listener)
}

/* LISTS DIALOGS */

/**
 * Displays and returns a dialog with a single choice list of [choices] to choose from.
 *  Dialog may have a [title] and may show radio buttons depending on [showRadioButtons].
 *  A [currentChoice] can be given (-1 if none, defaults to -1). When a choice is selected,
 *  [onChoiceSelected] is called
 */
fun Context.singleListDialog(
    choices: List<String>,
    @StringRes title: Int = -1,
    currentChoice: Int = -1,
    showRadioButtons: Boolean = true,
    onChoiceSelected: (position: Int) -> Unit
) = MaterialDialog(this).show {
    build(title)

    if (showRadioButtons) {
        listItemsSingleChoice(items = choices, initialSelection = currentChoice) { _, index, _ ->
            onChoiceSelected(index)
        }
    } else {
        listItems(items = choices) { _, index, _ ->
            onChoiceSelected(index)
        }
    }
}

/**
 * Displays and returns a dialog with a multiple choice list of [choices] to choose from.
 *  Dialog may have a [title] and has one [button] (text defaults to the Android OK). A list of
 *  [selectedItems] can be given (defaults to an empty list). When the user has clicked on the
 *  button, [onChoicesSelected] is called with the list of selected choices.
 */
fun Context.multiListDialog(
    choices: List<String>,
    @StringRes title: Int = -1,
    @StringRes button: Int = android.R.string.ok,
    selectedItems: IntArray = intArrayOf(),
    onChoicesSelected: (positions: IntArray) -> Unit
) = MaterialDialog(this).show {
    build(title)
    listItemsMultiChoice(items = choices, initialSelection = selectedItems) { _, indices, _ ->
        onChoicesSelected(indices)
    }
    positiveButton(button)
}

/**
 * Begins the construction of a [MaterialDialog] with an optional [title] and [message]
 */
private fun MaterialDialog.build(
    @StringRes title: Int,
    @StringRes message: Int = 0
) {
    if (title != 0) {
        title(title)
    }

    if (message != 0) {
        message(message)
    }
}

/**
 * Begins the construction of a [MaterialDialog] with an optional [title] and [message]
 */
private fun MaterialDialog.build(
    @StringRes title: Int,
    message: String?
) {
    build(title)
    message?.let { message(text = message) }
}
