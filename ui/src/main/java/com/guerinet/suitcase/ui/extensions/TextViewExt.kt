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

package com.guerinet.suitcase.ui.extensions

import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.widget.TextView

/**
 * TextView extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * Sets a tint of the [color] on the drawable at the relative [position]
 *  0: Start, 1: Top, 2: End, 3: Bottom
 */
fun TextView.setDrawableTint(position: Int, @ColorInt color: Int) {
    val drawables = compoundDrawablesRelative

    // Tint the necessary one
    drawables[position] = drawables[position].setTintCompat(color)

    // Set the drawables back
    setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawables[2],
            drawables[3])
}

/**
 * Sets the tint of a [colorId] on the drawable at the relative [position]
 *  0: Start, 1: Top, 2: End, 3: Bottom
 */
fun TextView.setDrawableTintId(position: Int, @ColorRes colorId: Int) =
        setDrawableTint(position, ContextCompat.getColor(context, colorId))

/**
 * Sets the [start], [top], [end], and [bottom] drawables
 */
fun TextView.setDrawables(start: Drawable? = compoundDrawablesRelative[0],
        top: Drawable? = compoundDrawablesRelative[1],
        end: Drawable? = compoundDrawablesRelative[2],
        bottom: Drawable? = compoundDrawablesRelative[3]) =
        setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)

/**
 * Sets the [start], [top], [end], and [bottom] drawable Ids (Warning: this will overwrite
 *  previously set drawables)
 */
fun TextView.setDrawableIds(start: Int = 0,
        top: Int = 0,
        end: Int = 0,
        bottom: Int = 0) = setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)

/**
 * Changes the [TextView]'s text size by using the [textSizeId]
 */
fun TextView.setTextSizeId(@DimenRes textSizeId: Int) =
        setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(textSizeId))