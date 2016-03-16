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

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okio.BufferedSource;
import okio.Okio;

/**
 * Static utility classes
 * @author Julien Guerinet
 * @since 1.0.0
 */
public class Utils {

    /**
     * Displays a toast of short length
     *
     * @param context App context
     * @param message Message to show
     */
    public static void toast(Context context, @StringRes int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a toast of Short length
     *
     * @param context App context
     * @param message Message to show
     */
    public static void toast(Context context, @NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Opens a given URL
     *
     * @param context App context
     * @param url      URL to open
     */
    public static void openURL(Context context, String url) {
        //Check that http:// or https:// is there
        if (!url.toLowerCase().startsWith(("http://").toLowerCase()) &&
                !url.toLowerCase().startsWith(("https://").toLowerCase())) {
            url = "http://" + url;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse(url));
        context.startActivity(intent);
    }

    /**
     * Opens the URL to an app in the Play Store or in the browser if the Play Store does not exist
     *
     * @param context     App context
     * @param packageName App package name
     */
    public static void openPlayStoreApp(Context context, String packageName) {
        try {
            context.startActivity(new Intent(
                    Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch(ActivityNotFoundException e) {
            //If the user user dot have the play store installed, open it in their browser
            openURL(context, "https://play.google.com/store/apps/details?id=" + packageName);
        }
    }

    /**
     * Opens the URL to the app this context represents in the Play Store of in the browser if the
     *  Play Store does not exist
     * @param context App context, which will be used to get the package name
     */
    public static void openPlayStoreApp(Context context) {
        openPlayStoreApp(context, context.getPackageName());
    }

    /**
     * @param context App context
     * @return App package info, null if an error
     */
    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch(PackageManager.NameNotFoundException e) {
            Log.e("Utils", "Package Info not found", e);
            return null;
        }
    }

    /**
     * @param context App context
     * @return App version code
     */
    public static int versionCode(Context context) {
        PackageInfo info = getPackageInfo(context);
        return info == null ? -1 : info.versionCode;
    }

    /**
     * @param context App context
     * @return App version name
     */
    public static String versionName(Context context) {
        PackageInfo info = getPackageInfo(context);
        return info == null ? null : info.versionName;
    }

    /**
     * Tints the drawable with the given color
     *
     * @param drawable The drawable to tint
     * @param color    The new color
     */
    public static void setTint(Drawable drawable, @ColorInt int color) {
        //Wrap the drawable in the compat library
        drawable = DrawableCompat.wrap(drawable).mutate();

        //Tint the drawable with the compat library
        DrawableCompat.setTint(drawable, color);
    }

    /**
     * Tints the compound drawable at the given position of a TextView
     *
     * @param textView The TextView with the compound drawable
     * @param position The position of the compound drawable (0: left, 1: top, 2: right, 3: bottom)
     * @param color    The new color
     */
    public static void setTint(TextView textView, int position, @ColorInt int color) {
        setTint(textView.getCompoundDrawables()[position], color);
    }

    /**
     * Deletes the contents of a folder and possibly the folder itself
     *
     * @param folder The folder to recursively delete
     * @param deleteFolder True if the parent folder should be deleted as well, false otherwise
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteFolderContents(File folder, boolean deleteFolder) {
        File[] files = folder.listFiles();
        if (files != null) {
            //Go through the folder's files
            for (File f: files) {
                if (f.isDirectory()) {
                    //Recursively call this method if the file is a folder
                    deleteFolderContents(f, true);
                } else {
                    //Delete the file if it is a file
                    f.delete();
                }
            }
        }
        //Only delete the folder if necessary
        if (deleteFolder) {
            folder.delete();
        }
    }

    /**
     * @param folder The folder to recursively delete
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteFolder(File folder) {
        deleteFolderContents(folder, true);
    }

    /**
     * Returns a folder from the external files directory, creates it before returning it
     * if it doesn't exist
     *
     * @param context    App context
     * @param folderName Folder name
     * @param type       Folder type
     * @return The folder
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File getFolder(Context context, String folderName, @Nullable String type) {
        File folder = new File(context.getExternalFilesDir(type), folderName);

        //Create it if it doesn't exist already or it isn't a directory
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
        }

        return folder;
    }

    /**
     * Returns URL params in a String format
     *
     * @param params           List of name-value pairs containing all of the keys and associated
     *                         values
     * @return The query String
     */
    public static String getQuery(List<Pair<String, String>> params) {
        try {
            StringBuilder result = new StringBuilder();
            boolean first = true;

            //Add the original '?'
            result.append("?");

            //Go through the pairs
            for (Pair<String, String> pair : params) {
                if (first) {
                    //First only happens once to avoid the first '&'
                    first = false;
                } else {
                    //If not first, add the & to chain them together
                    result.append("&");
                }

                //Append the key and value
                result.append(URLEncoder.encode(pair.first, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.second, "UTF-8"));
            }

            return result.toString();
        } catch(UnsupportedEncodingException e) {
            Log.e("Utils", "Unsupported encoding while getting the query params", e);
            return "";
        }
    }

    /**
     * @param manager The {@link ConnectivityManager} instance
     * @return True if the user is connected to the internet, false otherwise
     */
    public static boolean isConnected(ConnectivityManager manager) {
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    /**
     * Returns a BufferedSource for a file in the raw folder (can be used with Moshi for example)
     *
     * @param context App context
     * @param fileId  Resource Id of the file to read from the raw folder
     * @return The {@link BufferedSource}
     */
    public static BufferedSource sourceFromRaw(Context context, @RawRes int fileId) {
        return Okio.buffer(Okio.source(context.getResources().openRawResource(fileId)));
    }

    /**
     * Reads a String from a file in the raw folder
     *
     * @param context App context
     * @param fileId  Resource Id of the file to read from the raw folder
     * @return The contents of the file in String format
     */
    public static String stringFromRaw(Context context, @RawRes int fileId) {
        try {
            return sourceFromRaw(context, fileId).readUtf8();
        } catch(IOException e) {
            Log.e("Utils", "Error reading String from raw", e);
        }
        return null;
    }

    /* MARSHMALLOW PERMISSIONS */

    /**
     * Checks if a given permission is granted
     *
     * @param context    App context
     * @param permission Permission to check
     * @return True if the permission is granted, false otherwise
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Checks if the given permission has been granted, and requests it if it hasn't
     *
     * @param activity    Calling activity
     * @param permission  Permission needed
     * @param requestCode Request code to use if we need to ask for the permission
     * @return True if the permission has already been granted, false otherwise
     */
    public static boolean requestPermission(Activity activity, String permission, int requestCode) {
        //Check that we have the permission
        if (!isPermissionGranted(activity, permission)) {
            //Request the permission
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            return false;
        }
        //If we already have the permission, return true
        return true;
    }
}
