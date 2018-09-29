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

import android.content.Context
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog

/**
 * Context extensions relating to dialogs
 * @author Julien Guerinet
 * @since 2.5.0
 */

/* NEUTRAL */

/**
 * Displays and returns a dialog with one button. This dialog can have a [title], a [message],
 *  a [button] text (defaults to the Android OK), and a button [listener].
 */
fun Context.neutralDialog(@StringRes title: Int = 0, @StringRes message: Int = 0,
        @StringRes button: Int = android.R.string.ok,
        listener: ((dialog: MaterialDialog, which: DialogAction) -> Unit)? = null): MaterialDialog {
    return build(title, message, listener)
            .neutralText(button)
            .show()
}

/**
 * Displays and returns a dialog with one button. This dialog can have a [title], a [message],
 *  a [button] text (defaults to the Android OK), and a button [listener]
 */
fun Context.neutralDialog(@StringRes title: Int = 0, message: String? = null,
        @StringRes button: Int = android.R.string.ok,
        listener: ((dialog: MaterialDialog, which: DialogAction) -> Unit)? = null): MaterialDialog {
    return build(title, message, listener)
            .neutralText(button)
            .show()
}

/* ALERT DIALOGS */

/**
 * Displays and returns a dialog with 2 buttons with a [positiveText] (defaults to the Android OK)
 *  and [negativeText] (defaults to the Android Cancel). This dialog can have a [title],
 *  a [message], and a [listener] for both buttons.
 */
fun Context.alertDialog(@StringRes title: Int = 0, @StringRes message: Int = 0,
        @StringRes positiveText: Int = android.R.string.ok,
        @StringRes negativeText: Int = android.R.string.cancel,
        listener: ((dialog: MaterialDialog, which: DialogAction) -> Unit)? = null): MaterialDialog {
    return build(title, message, listener)
            .positiveText(positiveText)
            .negativeText(negativeText)
            .show()
}

/**
 * Displays and returns a dialog with 2 buttons with a [positiveText] (defaults to the Android OK)
 *  and [negativeText] (defaults to the Android Cancel). This dialog can have a [title],
 *  a [message], and a [listener] for both buttons.
 */
fun Context.alertDialog(@StringRes title: Int = 0, message: String? = null,
        @StringRes positiveText: Int = android.R.string.ok,
        @StringRes negativeText: Int = android.R.string.cancel,
        listener: ((dialog: MaterialDialog, which: DialogAction) -> Unit)? = null): MaterialDialog {
    return build(title, message, listener)
            .positiveText(positiveText)
            .negativeText(negativeText)
            .show()
}

/* LISTS DIALOGS */

/**
 * Displays and returns a dialog with a single choice list of [choices] to choose from.
 *  Dialog may have a [title] and may show radio buttons depending on [showRadioButtons].
 *  A [currentChoice] can be given (-1 if none, defaults to -1). When a choice is selected,
 *  [onChoiceSelected] is called
 */
fun Context.singleListDialog(choices: Array<String>, @StringRes title: Int = -1,
        currentChoice: Int = -1, showRadioButtons: Boolean = true,
        onChoiceSelected: (position: Int) -> Unit): MaterialDialog {
    val builder = build(title)
            .items(*choices)

    if (showRadioButtons) {
        builder.itemsCallbackSingleChoice(currentChoice) { _, _, which, _ ->
            onChoiceSelected(which)
            true
        }
    } else {
        builder.itemsCallback { _, _, position, _ -> onChoiceSelected(position) }
    }
    return builder.show()
}

/**
 * Displays and returns a dialog with a multiple choice list of [choices] to choose from.
 *  Dialog may have a [title] and has one [button] (text defaults to the Android OK). A list of
 *  [selectedItems] can be given (defaults to an empty list). When the user has clicked on the
 *  button, [onChoicesSelected] is called with the list of selected choices.
 */
fun Context.multiListDialog(choices: Array<String>, @StringRes title: Int = -1,
        @StringRes button: Int = android.R.string.ok, selectedItems: Array<Int> = arrayOf(),
        onChoicesSelected: (positions: Array<Int>) -> Unit): MaterialDialog {
    return build(title)
            .items(*choices)
            .itemsCallbackMultiChoice(selectedItems) { _, which, _ ->
                onChoicesSelected(which)
                true
            }
            .positiveText(button)
            .show()
}

/**
 * Constructs and returns the base part of the builder by setting the [title] and [message],
 *  as well as the [listener] for the buttons
 */
private fun Context.build(@StringRes title: Int, @StringRes message: Int = 0,
        listener: ((dialog: MaterialDialog, which: DialogAction) -> Unit)? = null):
        MaterialDialog.Builder {
    val builder = MaterialDialog.Builder(this)

    // Set the title, message, and/or button listener if they are present
    if (title != 0) {
        builder.title(title)
    }
    if (message != 0) {
        builder.content(message)
    }

    if (listener != null) {
        builder.onAny(listener)
    }

    return builder
}

/**
 * Constructs and returns the base part of the builder by setting the [title] and [message],
 *  as well as the [listener] for the buttons
 */
private fun Context.build(@StringRes title: Int, message: String?,
        listener: ((dialog: MaterialDialog, which: DialogAction) -> Unit)? = null):
        MaterialDialog.Builder {
    val builder = build(title, listener = listener)

    // Set the message if there is one
    message?.apply { builder.content(this) }

    return builder
}