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

package com.guerinet.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Static utility classes
 * @author Julien Guerinet
 * @since 1.0.0
 */
public class Utils {

    /**
     * Tints the drawable with the given color
     *
     * @param drawable The drawable to tint
     * @param color    The new color
     * @return The wrapped drawable with the tint
     */
    public static Drawable setTint(Drawable drawable, @ColorInt int color) {
        // Wrap the drawable in the compat library
        drawable = DrawableCompat.wrap(drawable).mutate();

        // Tint the drawable with the compat library
        DrawableCompat.setTint(drawable, color);

        return drawable;
    }

    /**
     * Tints and sets the image in an {@link ImageView}
     *
     * @param imageView The {@link ImageView} with the drawable to tint
     * @param color     The new color
     */
    public static void setTint(ImageView imageView, @ColorInt int color) {
        imageView.setImageDrawable(setTint(imageView.getDrawable(), color));
    }

    /**
     * Tints and sets the image on a {@link MenuItem}
     *
     * @param item  The {@link MenuItem} with the drawable to change
     * @param color The new color
     */
    public static void setTint(MenuItem item, @ColorInt int color) {
        item.setIcon(setTint(item.getIcon(), color));
    }

    /**
     * Tints the compound drawable at the given position of a TextView
     *
     * @param textView The TextView with the compound drawable
     * @param position The position of the compound drawable (0: left, 1: top, 2: right, 3: bottom)
     * @param color    The new color
     */
    public static void setTint(TextView textView, int position, @ColorInt int color) {
        Drawable drawable = setTint(textView.getCompoundDrawables()[position], color);

        // Set the drawable back
        Drawable left = null, top = null, right = null, bottom = null;
        switch (position) {
            case 0:
                left = drawable;
                break;
            case 1:
                top = drawable;
                break;
            case 2:
                right = drawable;
                break;
            case 3:
                bottom = drawable;
                break;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }
}
