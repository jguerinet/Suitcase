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
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

/**
 * Static utility methods to quickly create {@link AlertDialog}s
 * @author Julien Guerinet
 * @since 1.0.0
 */
public class DialogUtils {

    /**
     * Constructs the base part of the builder, taking into account possibly null title and messages
     * @param context App context
     * @param title   Dialog title, -1 if none
     * @param message Dialog message, -1 if none
     * @return The {@link AlertDialog.Builder} instance
     */
    private static AlertDialog.Builder build(Context context, @StringRes int title,
            @StringRes int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Set the title and/or message if they are present
        if (title != -1) {
            builder.setTitle(title);
        }
        if (message != -1) {
            builder.setMessage(message);
        }

        return builder;
    }

    /**
     * Constructs the base part of the builder, taking into account possibly null title and messages
     *
     * @param context App context
     * @param title   Dialog title, -1 if none
     * @param message Dialog message, null if none
     * @return The {@link AlertDialog.Builder} instance
     */
    private static AlertDialog.Builder build(Context context, @StringRes int title,
            String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Set the title and/or message if they are present
        if (title != -1) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }

        return builder;
    }

    /* NEUTRAL DIALOGS */

    /**
     * Displays an {@link AlertDialog} with one 'ok' button
     *
     * @param context  App context
     * @param title    Dialog title, -1 if none
     * @param message  Dialog message, -1 if none
     * @param listener {@link DialogInterface.OnClickListener} for the button
     * @return The {@link AlertDialog} instance
     */
    public static AlertDialog neutral(Context context, @StringRes int title, @StringRes int message,
            DialogInterface.OnClickListener listener) {
        return build(context, title, message)
                .setNeutralButton(android.R.string.ok, listener)
                .show();
    }

    /**
     * Displays an {@link AlertDialog} with one 'ok' button
     *
     * @param context App context
     * @param title   Dialog title, -1 if none
     * @param message Dialog message, -1 if none
     * @return The {@link AlertDialog} instance
     */
    public static AlertDialog neutral(Context context, @StringRes int title,
            @StringRes int message) {
        return neutral(context, title, message, null);
    }

    /**
     * Displays an {@link AlertDialog} with one 'ok' button
     *
     * @param context App context
     * @param title   Dialog title, -1 if none
     * @param message Dialog message, null if none
     * @return The {@link AlertDialog} instance
     */
    public static AlertDialog neutral(Context context, @StringRes int title,
            @Nullable String message) {
        return build(context, title, message)
                .setNeutralButton(android.R.string.ok, null)
                .show();
    }

    /* ALERT DIALOGS */

    /**
     * Displays a {@link AlertDialog} with two buttons
     *
     * @param context        App context
     * @param title          Dialog title, -1 if none
     * @param message        Dialog message, -1 if none
     * @param positiveButton Dialog's positive button text
     * @param negativeButton Dialog's negative button text
     * @param listener       {@link DialogInterface.OnClickListener} for both buttons
     * @return The {@link AlertDialog} instance
     */
    public static AlertDialog alert(Context context, @StringRes int title, @StringRes int message,
            @StringRes int positiveButton, @StringRes int negativeButton,
            DialogInterface.OnClickListener listener) {
        return build(context, title, message)
                .setPositiveButton(positiveButton, listener)
                .setNegativeButton(negativeButton, listener)
                .show();
    }

    /**
     * Displays an {@link AlertDialog} with two buttons: 'ok' and 'cancel'
     *
     * @param context  App context
     * @param title    Dialog title, -1 if none
     * @param message  Dialog message, -1 if none
     * @param listener {@link DialogInterface.OnClickListener} for both buttons
     * @return The {@link AlertDialog} instance
     */
    public static AlertDialog alert(Context context, @StringRes int title, @StringRes int message,
            DialogInterface.OnClickListener listener) {
        return alert(context, title, message, android.R.string.ok, android.R.string.cancel,
                listener);
    }

    /**
     * Displays an {@link AlertDialog} with two buttons
     *
     * @param context        App context
     * @param title          Dialog title, -1 if none
     * @param message        Dialog message, null if none
     * @param positiveButton Dialog's positive button text
     * @param negativeButton Dialog's negative button text
     * @param listener       {@link DialogInterface.OnClickListener} for both buttons
     * @return The {@link AlertDialog} instance
     */
    public static AlertDialog alert(Context context, @StringRes int title, String message,
            @StringRes int positiveButton, @StringRes int negativeButton,
            DialogInterface.OnClickListener listener) {
        return build(context, title, message)
                .setPositiveButton(positiveButton, listener)
                .setNegativeButton(negativeButton, listener)
                .show();
    }

    /* LIST DIALOGS */

    /**
     * Displays an {@link AlertDialog} with a list of single choices to choose from
     * and which will close when a choice has been made
     *
     * @param context       App context
     * @param title         Dialog title, -1 if none
     * @param listInterface An implementation of the {@link ListDialogInterface}
     * @return The {@link AlertDialog} instance
     */
    public static AlertDialog list(Context context, @StringRes int title,
            final ListDialogInterface listInterface) {
        return build(context, title, -1)
                .setSingleChoiceItems(listInterface.getChoices(), listInterface.getCurrentChoice(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listInterface.onChoiceSelected(which);
                                //Dismiss the dialog when a choice has been made
                                dialog.dismiss();
                            }
                        })
                .show();
    }
}
