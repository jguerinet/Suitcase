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

/**
 * Interface for an item within the [RvAdapter]
 * @author Julien Guerinet
 * @since 5.0.0
 */
interface RvItem<T> {

    /** Item this represents */
    val item: T

    /**
     * Returns the view type that is associated to this item. Defaults to -1
     */
    fun getViewType(): Int = -1

    /**
     * Returns true if the [item] is the same as this item, false otherwise. Defaults to false
     */
    fun isSameItem(item: RvItem<T>): Boolean = false

    /**
     * Returns true if the [item] has the same contents as this item, false otherwise.
     *  Defaults to checking is the [item]s are equal
     */
    fun isSameContents(item: RvItem<T>): Boolean {
        // Default implementation is to check if the items are equal
        return this.item == item.item
    }
}
