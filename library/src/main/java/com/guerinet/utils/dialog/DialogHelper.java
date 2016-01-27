/*
 * Copyright 2016 Julien Guerinet
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

package com.guerinet.utils.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

/**
 * Static helper methods to quickly create {@link AlertDialog}s
 * @author Julien Guerinet
 * @since 1.0.0
 */
public class DialogHelper {
    /* NEUTRAL DIALOGS */

    /**
     * Displays an {@link AlertDialog} with one 'ok' button
     *
     * @param context  App context
     * @param title    Dialog title
     * @param message  Dialog message
     * @param listener {@link DialogInterface.OnClickListener} for the button
     */
    public static void neutral(Context context, @StringRes int title, @StringRes int message,
            DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(android.R.string.ok, listener)
                .show();
    }

    /**
     * Displays an {@link AlertDialog} with one 'ok' button
     *
     * @param context App context
     * @param title   Dialog title
     * @param message Dialog message
     */
    public static void neutral(Context context, @StringRes int title, @StringRes int message) {
        neutral(context, title, message, null);
    }

    /* ALERT DIALOGS */

    /**
     * Displays a {@link AlertDialog} with two buttons
     *
     * @param context        App context
     * @param title          Dialog title
     * @param message        Dialog message
     * @param positiveButton Dialog's positive button text
     * @param negativeButton Dialog's negative button text
     * @param listener       {@link DialogInterface.OnClickListener} for both buttons
     */
    public static void alert(Context context, @StringRes int title, @StringRes int message,
            @StringRes int positiveButton, @StringRes int negativeButton,
            DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, listener)
                .setNegativeButton(negativeButton, listener)
                .show();
    }

    /**
     * Displays an {@link AlertDialog} with two buttons: 'ok' and 'cancel'
     *
     * @param context  App context
     * @param title    Dialog title
     * @param message  Dialog message
     * @param listener {@link DialogInterface.OnClickListener} for both buttons
     */
    public static void alert(Context context, @StringRes int title, @StringRes int message,
            DialogInterface.OnClickListener listener) {
        alert(context, title, message, android.R.string.ok, android.R.string.cancel, listener);
    }

    /* LIST DIALOGS */

    /**
     * Displays an {@link AlertDialog} with a list of single choices to choose from
     * and which will close when a choice has been made
     *
     * @param context       App context
     * @param title         Dialog title
     * @param listInterface An implementation of the {@link ListDialogInterface}
     */
    public static void list(Context context, @StringRes int title,
            final ListDialogInterface listInterface) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(listInterface.getChoices(), listInterface.getCurrentChoice(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listInterface.onChoiceSelected(which);
                                //Dismiss the dialog when a choice has been made
                                dialog.dismiss();
                            }
                        });
    }
}
