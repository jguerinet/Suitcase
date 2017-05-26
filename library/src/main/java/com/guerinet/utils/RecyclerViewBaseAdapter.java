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

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Basic adapter implementation for {@link RecyclerView}s
 * @author Julien Guerinet
 * @since 1.0.0
 */
public abstract class RecyclerViewBaseAdapter extends
        RecyclerView.Adapter<RecyclerViewBaseAdapter.BaseHolder> {
    /**
     * View shown if there are no items to show, can be empty
     */
    @Nullable
    private final View emptyView;

    /**
     * Default Constructor
     *
     * @param emptyView Empty view, can be null
     */
    public RecyclerViewBaseAdapter(@Nullable View emptyView) {
        this.emptyView = emptyView;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * Called when the list needs to be updated
     *  No default implementation but can be implemented by the subclasses
     */
    public void update() {}

    /**
     * @param visible True if the empty view should be visible, false otherwise
     */
    public void showEmptyView(boolean visible) {
        if (emptyView != null) {
            emptyView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * Basic implementation of the {@link RecyclerView.ViewHolder}
     */
    public abstract class BaseHolder extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * @param position Binds the object at the given position to the view
         */
        public abstract void bind(int position);
    }
}
