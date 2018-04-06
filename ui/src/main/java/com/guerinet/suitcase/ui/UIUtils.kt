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

package com.guerinet.suitcase.ui

import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.guerinet.suitcase.ui.extensions.setDrawableTint
import com.guerinet.suitcase.ui.extensions.setTint
import com.guerinet.suitcase.ui.extensions.setTintCompat

/**
 * Static UI Utility methods
 * @author Julien Guerinet
 * @since 2.0.0
 */
@Deprecated("Replaced by extensions")
object UIUtils {

    /**
     * @return Wrapped [Drawable] with the tint of the given [color]
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith("drawable.setTintCompat(color)",
            "com.guerinet.suitcase.ui.extensions.setTintCompat"))
    fun setTint(drawable: Drawable, @ColorInt color: Int) = drawable.setTintCompat(color)

    /**
     * Tints and sets the image with the given [color] in the given [imageView]
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith("imageView.setTint(color)",
            "com.guerinet.suitcase.ui.extensions.setTint"))
    fun setTint(imageView: ImageView, @ColorInt color: Int) =
            imageView.setTint(color)

    /**
     * Tings and sets the image with the given [color] in the given menu [item]
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith("item.setTint(color)",
            "com.guerinet.suitcase.ui.extensions.setTint"))
    fun setTint(item: MenuItem, @ColorInt color: Int) =
            item.setTint(color)

    /**
     * Tints the compound drawable at a given [position] (0: left, 1: top, 2: right, 3: bottom)
     *  of a [textView] with the given [color]
     */
    @JvmStatic
    @Deprecated("Replaced by extension", ReplaceWith("textView.setDrawableTint(position, color)",
            "com.guerinet.suitcase.ui.extensions.setDrawableTint"))
    fun setTint(textView: TextView, position: Int, @ColorInt color: Int) =
            textView.setDrawableTint(position, color)
}