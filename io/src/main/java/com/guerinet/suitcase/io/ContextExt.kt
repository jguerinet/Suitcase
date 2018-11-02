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
import androidx.annotation.RawRes
import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.File
import java.io.IOException

/**
 * Context extensions for IO operations
 * @author Julien Guerinet
 * @since 2.4.0
 */

/**
 * Returns a [BufferedSource] for a file with the given [fileId] within the raw folder
 */
fun Context.sourceFromRaw(@RawRes fileId: Int): BufferedSource =
    resources.openRawResource(fileId).source().buffer()

/**
 * Attempts to read and return a String from a file with the given [fileId] within the raw folder.
 *  Throws an [IOException] if there was an error during the read
 */
@Throws(IOException::class)
fun Context.stringFromRaw(@RawRes fileId: Int): String = sourceFromRaw(fileId).readUtf8()

/**
 * Returns a file (or folder if [isFolder] is true) with the given [name] and [type] (null if it
 *  does not have a specific type, defaults to null).
 *  This will create the file/folder if it doesn't exist already.
 */
fun Context.getFile(isFolder: Boolean, name: String, type: String? = null): File {
    val file = File(getExternalFilesDir(type), name)

    if (!isFolder && !file.exists()) {
        // If it's supposed to be a file and it doesn't exist, create it
        file.createNewFile()
    } else if (isFolder && (!file.exists() || !file.isDirectory)) {
        // If it's supposed to be a folder and it doesn't exist or isn't a folder, create it
        file.mkdirs()
    }
    return file
}
