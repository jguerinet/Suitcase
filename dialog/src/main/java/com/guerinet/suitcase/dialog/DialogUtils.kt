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
import android.support.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog

/**
 * Static utility methods to quickly create AlertDialogs
 * @author Julien Guerinet
 * @since 2.0.0
 */
@Deprecated("Replaced with extensions")
object DialogUtils {

    /* NEUTRAL */

    /**
     * Displays a dialog using the app [context] with one button. This dialog can have a [title],
     *  a [message], and a [listener] for the neutral button
     *
     *  @return Displayed dialog
     */
    @JvmStatic
    @JvmOverloads
    @Deprecated("Replaced with extension",
            ReplaceWith("context.neutralDialog(title, message, listener = listener)"))
    fun neutral(context: Context, @StringRes title: Int = -1, @StringRes message: Int = -1,
            listener: MaterialDialog.SingleButtonCallback? = null): MaterialDialog =
            context.neutralDialog(title, message, listener = listener)

    /**
     * Displays a dialog using the app [context] with one button. This dialog can have a [title],
     *  a [message], and a [listener] for the neutral button
     *
     *  @return Displayed dialog
     */
    @JvmStatic
    @Deprecated("Replaced by extension",
            ReplaceWith("context.neutralDialog(title, message, listener = listener)"))
    fun neutral(context: Context, @StringRes title: Int = -1, message: String? = null,
            listener: MaterialDialog.SingleButtonCallback? = null): MaterialDialog =
            context.neutralDialog(title, message, listener = listener)

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
    @Deprecated("Replaced by extension", ReplaceWith(
            "context.alertDialog(title, message, listener, positiveText, negativeText)"))
    fun alert(context: Context, @StringRes title: Int = -1, @StringRes message: Int = -1,
              listener: MaterialDialog.SingleButtonCallback? = null,
              @StringRes positiveText: Int = android.R.string.ok,
            @StringRes negativeText: Int = android.R.string.cancel): MaterialDialog =
            context.alertDialog(title, message, listener, positiveText, negativeText)

    /**
     * Displays a dialog using the app [context] with 2 buttons with the given [positiveText] and
     * [negativeText]. This dialog can have a [title], a [message], and a [listener] for both
     * buttons
     *
     *  @return Displayed dialog
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith(
            "context.alertDialog(title, message, listener, positiveText, negativeText)"))
    fun alert(context: Context, @StringRes title: Int = -1, message: String? = null,
              listener: MaterialDialog.SingleButtonCallback? = null,
              @StringRes positiveText: Int = android.R.string.ok,
            @StringRes negativeText: Int = android.R.string.cancel): MaterialDialog =
            context.alertDialog(title, message, listener, positiveText, negativeText)

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
    @Deprecated("Replaced by extension",
            ReplaceWith("context.singleListDialog(title, listInterface, showRadioButtons)"))
    fun singleList(context: Context, @StringRes title: Int = -1, listInterface: SingleListInterface,
            showRadioButtons: Boolean = true): MaterialDialog =
            context.singleListDialog(title, listInterface, showRadioButtons)

    /**
     * Displays a dialog with the app [context] with a multiple choice list of items to choose from.
     *  Dialog may have a [title] and uses a [listInterface] to determine the list of choices
     *
     * @return Shown dialog
     */
    @JvmStatic
    @JvmOverloads
    @Deprecated("Replaced by extension",
            ReplaceWith("context.multiListDialog(title, listInterface)"))
    fun multiList(context: Context, @StringRes title: Int = -1, listInterface: MultiListInterface):
            MaterialDialog = context.multiListDialog(title, listInterface)
}