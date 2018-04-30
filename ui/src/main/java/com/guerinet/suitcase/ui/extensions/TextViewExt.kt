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

import android.support.annotation.ColorInt
import android.support.annotation.DimenRes
import android.util.TypedValue
import android.widget.TextView

/**
 * [TextView] extensions
 * @author Julien Guerinet
 * @since 2.3.0
 */

/**
 * Sets a tint of the given [color] on the drawable at the given relative [position]
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
 * Changes the [TextView]'s text size by using the [textSizeId]
 */
fun TextView.setTextSizeId(@DimenRes textSizeId: Int) {
    setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(textSizeId))
}