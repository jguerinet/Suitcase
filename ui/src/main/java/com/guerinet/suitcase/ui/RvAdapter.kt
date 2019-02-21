/*
 * Copyright 2016-2019 Julien Guerinet
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

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * Simple adapter for a RecyclerView. Works with the [RvItem].
 * @author Julien Guerinet
 * @since 4.10.0
 */
abstract class RvAdapter : ListAdapter<RvItem<Any>, BaseListAdapter.BaseHolder<Any>>(ItemCallback()) {

    override fun onBindViewHolder(holder: BaseListAdapter.BaseHolder<Any>, position: Int) =
        holder.bind(position, getItem(position).item)

    override fun getItemViewType(position: Int): Int = getItem(position).getViewType()

    class ItemCallback : DiffUtil.ItemCallback<RvItem<Any>>() {

        override fun areItemsTheSame(oldItem: RvItem<Any>, newItem: RvItem<Any>): Boolean =
            isSameItemClass(oldItem, newItem) && oldItem.isSameItem(newItem)

        override fun areContentsTheSame(oldItem: RvItem<Any>, newItem: RvItem<Any>): Boolean =
            isSameItemClass(oldItem, newItem) && oldItem.isSameContents(newItem)

        /**
         * Returns true if the [oldItem] and [newItem]'s items are of the same class, false otherwise
         */
        private fun isSameItemClass(oldItem: RvItem<Any>, newItem: RvItem<Any>) =
            oldItem.item.javaClass == newItem.item.javaClass

    }

}

