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

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.animation.Interpolator
import android.widget.Scroller

/**
 * [ViewPager] implementation that has a custom speed for scrolling
 * @author Julien Guerinet
 * @since 2.0.0
 */
open class CustomDurationViewPager : ViewPager {
    private var scroller : CustomDurationScroller? = null

    init {
        try {
            // Make the necessary private fields accessible
            val scrollerField = ViewPager::class.java.getDeclaredField("mScroller")
            scrollerField.isAccessible = true

            val interpolatorField = ViewPager::class.java.getDeclaredField("sInterpolator")
            interpolatorField.isAccessible = true

            // Create our custom scroller and set it back onto the ViewPager
            scroller = CustomDurationScroller(context, interpolatorField.get(null) as Interpolator)
            @Suppress("LeakingThis")
            scrollerField.set(this, scroller)
        } catch (ignored: Exception) {}
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    /**
     * Sets the [scrollFactor] by which the scroll duration should change (total scroll duration
     *  will be scrollFactor * NORMAL_DURATION)
     */
    fun setScrollDurationFactor(scrollFactor: Double) {
        scroller?.scrollFactor = scrollFactor
    }

    private class CustomDurationScroller(context: Context, interpolator: Interpolator) :
            Scroller(context, interpolator) {
        /**
         * Factor by which we are slowing down or speeding up the scrolling. Default is 1
         */
        internal var scrollFactor : Double = 1.0

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, (duration * scrollFactor).toInt())
        }
    }
}