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

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Basic RecyclerView adapter implementation for [RecyclerView]s
 * @author Julien Guerinet
 * @since 2.0.0
 */
abstract class BaseRecyclerViewAdapter(val emptyView: View?) :
        RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseHolder>() {

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(position)
    }

    /**
     * @param [visible] True if the empty view should be visible, false otherwise
     */
    fun showEmptyView(visible: Boolean) {
        emptyView?.visibility = if (visible) View.VISIBLE else View.GONE
    }

    /**
     * Basic implementation of the [RecyclerView.ViewHolder]
     */
    abstract class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Constructor taking the [parent] and the [layoutId] of the item view to create
         */
        constructor(parent: ViewGroup, @LayoutRes layoutId: Int) :
                this(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))

        /**
         * Binds this holder to the given [position]
         */
        abstract fun bind(position: Int)
    }
}