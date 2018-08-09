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

import android.support.annotation.DimenRes
import android.view.View
import android.view.ViewGroup

/**
 * View extensions
 * @author Julien Guerinet
 * @since 2.4.1
 */

/**
 * Quickly sets the [width] and [height] (warning: this creates new [ViewGroup.LayoutParams])
 */
fun View.setWidthAndHeight(width: Int, height: Int) {
    layoutParams = ViewGroup.LayoutParams(width, height)
}

/**
 * Sets the padding with [paddingId] on all 4 sides of the view
 */
fun View.setPaddingId(@DimenRes paddingId: Int) {
    val padding = resources.getDimensionPixelOffset(paddingId)
    setPadding(padding, padding, padding, padding)
}