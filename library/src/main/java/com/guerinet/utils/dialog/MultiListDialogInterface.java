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

/**
 * Interface used for creating multiple choice list dialogs.
 * @author Julien Guerinet
 * @since 1.0.28
 */
public interface MultiListDialogInterface {
    /**
     * @return Array of booleans corresponding to the list of choices, with true if that choice
     * is selected and false if it is not
     */
    boolean[] getSelectedItems();

    /**
     * @return List of {@link CharSequence}s to display as the choices
     */
    CharSequence[] getChoices();

    /**
     * Called when a user has chosen an option
     *
     * @param position Position of the selected choice
     * @param checked  True if the choice is checked, false otherwise
     */
    void onChoiceSelected(int position, boolean checked);

    /**
     * Called when the user has closed the dialog
     */
    void onCloseDialog();
}
