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

package com.guerinet.utils.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * ViewPager implementation that has a custom speed for the scrolling
 * @author Julien Guerinet
 * @since 1.0.21
 */
public class CustomDurationViewPager extends ViewPager {
    private CustomDurationScroller scroller = null;

    /**
     * Default Constructor
     *
     * @param context App context
     */
    public CustomDurationViewPager(Context context) {
        super(context);
        initialize();
    }

    /**
     * @param context App context
     * @param attrs   Attribute set to use
     */
    public CustomDurationViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    /**
     * Overrides the Scroller instance with our own class so we can change the duration
     */
    private void initialize() {
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            this.scroller = new CustomDurationScroller(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(this, this.scroller);
        } catch (Exception ignored) {}
    }

    /**
     * Sets the factor by which the duration will change
     *
     * @param scrollFactor Scroll factor by which we will slow down or speed up the duration of the
     *                     scroll (total scroll duration will be scrollFactor * NORMAL_DURATION)
     */
    public void setScrollDurationFactor(double scrollFactor) {
        scroller.setScrollDurationFactor(scrollFactor);
    }

    class CustomDurationScroller extends Scroller {
        /**
         * Factor by which we are slowing down or speeding the scrolling. Default is 1
         */
        private double scrollFactor = 1;

        public CustomDurationScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        /**
         * Set the factor by which the duration will change
         * @param scrollFactor Scroll factor by which we will slow down or speed up the duration
         *                     of the scroll
         */
        public void setScrollDurationFactor(double scrollFactor) {
            this.scrollFactor = scrollFactor;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, (int) (duration * scrollFactor));
        }
    }
}
