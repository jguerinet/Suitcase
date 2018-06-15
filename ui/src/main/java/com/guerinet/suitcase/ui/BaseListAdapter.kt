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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Basic [ListAdapter] implementation for [RecyclerView]s
 * @author Julien Guerinet
 * @since 2.3.0
 *
 * @param diffCallback  [DiffUtil.ItemCallback] to use for the [ListAdapter]
 * @param emptyView     [View] to show if the list is empty, null if none (defaults to null)
 */
abstract class BaseListAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>,
                                  val emptyView: View? = null) :
        ListAdapter<T, BaseListAdapter.BaseHolder<T>>(diffCallback) {

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.bind(position, getItem(position))
    }

    override fun submitList(list: MutableList<T>?) {
        super.submitList(list)
        // Show the empty if an empty or null list is passed in
        showEmptyView(list?.isEmpty() ?: true)
    }

    /**
     * Determines whether the [emptyView], if there is one, [isVisible] or not
     */
    open fun showEmptyView(isVisible: Boolean) {
        emptyView?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    /**
     * Basic implementation of the [RecyclerView.ViewHolder]
     */
    open class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Convenience constructor taking the [parent] abd tge [layoutId] of the item view to create
         */
        constructor(parent: ViewGroup, @LayoutRes layoutId: Int) :
                this(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))

        /**
         * Binds the holder to the given [item] at the given [position]
         */
        open fun bind(position: Int, item: T) {}
    }
}