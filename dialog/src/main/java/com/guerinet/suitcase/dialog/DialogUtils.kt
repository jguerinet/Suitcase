/*
 * Copyright 2016-2017 Julien Guerinet
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
import android.support.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog

/**
 * Static utility methods to quickly create AlertDialogs
 * @author Julien Guerinet
 * @since 2.0.0
 */
object DialogUtils {

    /**
     * Constructs the base part of the builder using the app [context] by setting the [title] and
     *  [message] from the Strings, as well as the [listener] for the buttons
     *
     *  @return Builder instance
     */
    private fun build(context: Context, @StringRes title: Int, @StringRes message: Int = -1,
                      listener: MaterialDialog.SingleButtonCallback? = null):
            MaterialDialog.Builder {
        val builder = MaterialDialog.Builder(context)

        // Set the title, message, and/or button listener if they are present
        if (title != -1) {
            builder.title(title)
        }
        if (message != -1) {
            builder.content(message)
        }

        if (listener != null) {
            builder.onAny(listener)
        }

        return builder
    }

    /**
     * Constructs the base part of the builder using the app [context] by setting the [title] and
     *  [message], as well as the [listener] for the buttons
     *
     *  @return Builder instance
     */
    private fun build(context: Context, @StringRes title: Int = -1, message: String?,
                      listener: MaterialDialog.SingleButtonCallback? = null):
            MaterialDialog.Builder {
        val builder = build(context, title, listener = listener)

        // Set the message if there is one
        if (message != null) {
            builder.content(message)
        }

        return builder
    }

    /* NEUTRAL */

    /**
     * Displays a dialog using the app [context] with one button. This dialog can have a [title],
     *  a [message], and a [listener] for the neutral button
     *
     *  @return Displayed dialog
     */
    @JvmStatic
    @JvmOverloads
    fun neutral(context: Context, @StringRes title: Int = -1, @StringRes message: Int = -1,
                listener: MaterialDialog.SingleButtonCallback? = null): MaterialDialog {
        return build(context, title, message, listener)
                .neutralText(android.R.string.ok)
                .show()
    }

    /**
     * Displays a dialog using the app [context] with one button. This dialog can have a [title],
     *  a [message], and a [listener] for the neutral button
     *
     *  @return Displayed dialog
     */
    @JvmStatic
    fun neutral(context: Context, @StringRes title: Int = -1, message: String? = null,
                listener: MaterialDialog.SingleButtonCallback? = null): MaterialDialog {
        return build(context, title, message, listener)
                .neutralText(android.R.string.ok)
                .show()
    }

    /* ALERT DIALOGS */

    /**
     * Displays a dialog using the app [context] with 2 buttons with the given [positiveText] and
     * [negativeText]. This dialog can have a [title], a [message], and a [listener] for both
     * buttons. If the button texts are not provided, ok and cancel will be used
     *
     *  @return Displayed dialog
     */
    @JvmStatic
    @JvmOverloads
    fun alert(context: Context, @StringRes title: Int = -1, @StringRes message: Int = -1,
              @StringRes positiveText: Int = android.R.string.ok,
              @StringRes negativeText: Int = android.R.string.cancel,
              listener: MaterialDialog.SingleButtonCallback? = null): MaterialDialog {
        return build(context, title, message, listener)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .show()
    }

    /**
     * Displays a dialog using the app [context] with 2 buttons with the given [positiveText] and
     * [negativeText]. This dialog can have a [title], a [message], and a [listener] for both
     * buttons
     *
     *  @return Displayed dialog
     */
    @JvmStatic
    fun alert(context: Context, @StringRes title: Int = -1, message: String? = null,
              @StringRes positiveText: Int = android.R.string.ok,
              @StringRes negativeText: Int = android.R.string.cancel,
              listener: MaterialDialog.SingleButtonCallback? = null): MaterialDialog {
        return build(context, title, message, listener)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .show()
    }

    /* LISTS DIALOGS */

    /**
     * Displays a dialog with the app [context] with a single choice list of items to choose from.
     *  Dialog may have a [title] and uses a [listInterface] to determine the list of choices.
     *  May show radio buttons depending on [showRadioButtons]
     *
     * @return Shown dialog
     */
    @JvmStatic
    @JvmOverloads
    fun singleList(context: Context, @StringRes title: Int = -1, showRadioButtons: Boolean = true,
                   listInterface: SingleListInterface): MaterialDialog {
        val builder = build(context, title)
                .items(*listInterface.getChoices())

        if (showRadioButtons) {
            builder.itemsCallbackSingleChoice(listInterface.getCurrentChoice(),
                    { _, _, which, _ ->  listInterface.onChoiceSelected(which)
                        true
                    })
        } else {
            builder.itemsCallback({ _, _, position, _ ->  listInterface.onChoiceSelected(position)})
        }

        return builder.show()
    }

    /**
     * Displays a dialog with the app [context] with a multiple choice list of items to choose from.
     *  Dialog may have a [title] and uses a [listInterface] to determine the list of choices
     *
     * @return Shown dialog
     */
    @JvmStatic
    @JvmOverloads
    fun multiList(context: Context, @StringRes title: Int = -1, listInterface: MultiListInterface):
            MaterialDialog {
        return build(context, title)
                .items(*listInterface.getChoices())
                .itemsCallbackMultiChoice(listInterface.getSelectedItems(), { _, which, _ ->
                    listInterface.onChoicesSelected(which)
                    true
                })
                .neutralText(android.R.string.ok)
                .show()
    }
}