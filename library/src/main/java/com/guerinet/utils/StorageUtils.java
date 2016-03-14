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

package com.guerinet.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utility methods for loading and saving files to internal storage
 * @author Julien Guerinet
 * @since 1.0.18
 */
public class StorageUtils {

    /**
     * @param context  App context
     * @param fileName Internal storage file name
     * @param tag      Tag to use for any caught exception
     * @return The stream to the file, null if an error comes up
     */
    public static @Nullable FileInputStream load(Context context, String fileName, String tag) {
        try {
            //Return the FileInputStream from the given file name
            return context.openFileInput(fileName);
        } catch (FileNotFoundException e) {
            Log.e("Storage Utils Load", "Error: File not found: " + tag);
            return null;
        }
    }

    /**
     * Loads the object at the given file name
     *
     * @param context  App context
     * @param fileName File name
     * @param tag      Tag to use for any caught exception
     * @return The object loaded, null if none
     */
    @SuppressWarnings("unchecked")
    public static @Nullable Object loadObject(Context context, String fileName, String tag) {
        //If the FIS is null, don't continue
        FileInputStream fis = load(context, fileName, tag);
        if (fis == null) {
            return null;
        }

        //Set up the ObjectInputStream from the FileInputStream
        try {
            ObjectInputStream in = new ObjectInputStream(fis);
            return in.readObject();
        } catch (Exception e) {
            Log.e("Storage Utils Load", "Error loading " + tag + " from internal storage", e);
            return null;
        }
    }

    /**
     * Saves an object to internal storage
     *
     * @param context  App context
     * @param object   Object to save
     * @param fileName File name to save the object under
     * @param tag      Tag to use in case of an error
     */
    public static void saveObject(Context context, Object object, String fileName, String tag) {
        try {
            //Open a FileOutputStream to a file in internal storage
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            //Open an ObjectOutputStream using the FileOutputStream
            ObjectOutputStream out = new ObjectOutputStream(fos);
            //Write the object to the stream
            out.writeObject(object);
        } catch(Exception e) {
            Log.e("Storage Utils Save", "Error saving" + tag + " to internal storage", e);
        }
    }
}
