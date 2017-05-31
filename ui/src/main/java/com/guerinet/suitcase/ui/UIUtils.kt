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

package com.guerinet.suitcase.ui

import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

/**
 * Static UI Utility methods
 * @author Julien Guerinet
 * @since 2.0.0
 */
object UIUtils {

    /**
     * Tints the [drawable] with the given [color]
     *
     * @return The wrapped [Drawable] with the tint
     */
    @JvmStatic
    fun setTint(drawable: Drawable, @ColorInt color: Int): Drawable {
        // Wrap the drawable in the compat library
        val wrappedDrawable = DrawableCompat.wrap(drawable).mutate()

        // Tint the drawable
        DrawableCompat.setTint(wrappedDrawable, color)

        return drawable
    }

    /**
     * Tints and sets the image with the given [color] in the given [imageView]
     */
    @JvmStatic
    fun setTint(imageView: ImageView, @ColorInt color: Int) {
        imageView.setImageDrawable(setTint(imageView.drawable, color))
    }

    /**
     * Tings and sets the image with the given [color] in the given menu [item]
     */
    @JvmStatic
    fun setTint(item: MenuItem, @ColorInt color: Int) {
        item.icon = setTint(item.icon, color)
    }

    /**
     * Tints the compound drawable at a given [position] (0: left, 1: top, 2: right, 3: bottom)
     *  of a [textView] with the given [color]
     */
    @JvmStatic
    fun setTint(textView: TextView, position: Int, @ColorInt color: Int) {
        val drawables = textView.compoundDrawables

        // Tint the necessary one
        drawables[position] = setTint(drawables[position], color)

        // Set the drawables back
        textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2],
                drawables[3])
    }
}