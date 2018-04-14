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

package com.guerinet.suitcase.io

import android.content.Context
import android.support.annotation.RawRes
import java.io.IOException

/**
 * Static utility methods for any IO operations
 * @author Julien Guerinet
 * @since 2.0.0
 */
@Deprecated("Replaced with extensions")
object IOUtils {

    /**
     * @return A BufferedSource for a file with the given [fileId] in the raw folder, using the app
     *  [context]
     */
    @JvmStatic
    @Deprecated("Replaced with extension", ReplaceWith("context.sourceFromRaw(fileId)"))
    fun sourceFromRaw(context: Context, @RawRes fileId: Int) = context.sourceFromRaw(fileId)

    /**
     * @return A String for a file with the given [fileId] in the raw folder, using the app
     *  [context]
     * @throws IOException if there was an error reading the String from the file
     */
    @Throws(IOException::class)
    @JvmStatic
    @Deprecated("Replaced with extension", ReplaceWith("context.stringFromRaw(fileId)"))
    fun stringFromRaw(context: Context, @RawRes fileId: Int) = context.stringFromRaw(fileId)
}