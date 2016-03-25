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
import android.view.MotionEvent;

/**
 * A {@link ViewPager} that a user can't swipe through
 * @author Julien Guerinet
 * @version 1.0.0
 * @since 1.0.0
 */
public class NonSwipeableViewPager extends ViewPager {

    /**
     * Default Constructor
     *
     * @param context App context
     */
    public NonSwipeableViewPager(Context context) {
        super(context);
    }

    /**
     * Constructor with a set of attributes
     *
     * @param context App context
     * @param attrs   Set of attributes
     */
    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // Do not register any touch events
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Do no register any touch events
        return false;
    }
}
