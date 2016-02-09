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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;
import org.threeten.bp.temporal.Temporal;

import java.util.Locale;

/**
 * Static date utility classes
 * @author Julien Guerinet
 * @since 1.0.12
 */
public class DateUtils {
    /**
     * Private static singleton instance. Using a singleton structure to avoid re-initializing the
     *  formatters multiple times: they are lazily initialized only once and then reused
     */
    private static DateUtils instance;
    /**
     * {@link DateTimeFormatter} instance used for the medium date String
     */
    private DateTimeFormatter mediumDateFormatter;
    /**
     * {@link DateTimeFormatter} instance used for the long date String
     */
    private DateTimeFormatter longDateFormatter;
    /**
     * {@link DateTimeFormatter} intance used for the short time String
     */
    private DateTimeFormatter shortTimeFormatter;
    /**
     * {@link DateTimeFormatter} instance used for the RFC 1123 String
     */
    private DateTimeFormatter rfcFormatter;

    /**
     * Private Constructor
     */
    private DateUtils() {}

    /* GETTERS */

    /**
     * @return The {@link DateUtils} instance to use
     */
    private static DateUtils instance() {
        if (instance == null) {
            instance = new DateUtils();
        }
        return instance;
    }

    /**
     * @return The medium date {@link DateTimeFormatter} instance
     */
    private static DateTimeFormatter mediumDateFormatter() {
        if (instance().mediumDateFormatter == null) {
            instance().mediumDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        }
        return instance().mediumDateFormatter;
    }

    /**
     * @return The long date {@link DateTimeFormatter} instance
     */
    private static DateTimeFormatter longDateFormatter() {
        if (instance().longDateFormatter == null) {
            instance().longDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        }
        return instance().longDateFormatter;
    }

    /**
     * @return The short time {@link DateTimeFormatter} instance
     */
    private static DateTimeFormatter shortTimeFormatter() {
        if (instance().shortTimeFormatter == null) {
            instance().shortTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        }
        return instance().shortTimeFormatter;
    }

    /**
     * @return The RFC 1123 {@link DateTimeFormatter} instance
     */
    private static DateTimeFormatter rfcFormatter() {
        if (instance().rfcFormatter == null) {
            //We want to locale to always be US regardless of the user's locale
            instance().rfcFormatter = DateTimeFormatter.RFC_1123_DATE_TIME.withLocale(Locale.US);
        }
        return instance().rfcFormatter;
    }

    /* STATIC */

    /**
     * @param date The {@link LocalDate} to check
     * @return True if the date is in the past, false otherwise (today would return false)
     */
    public static boolean isPast(LocalDate date) {
        return LocalDate.now().isAfter(date);
    }

    /**
     * @param dateTime The {@link ZonedDateTime} to check
     * @return True if the date is in the past, false otherwise (right now would return false)
     */
    public static boolean isPast(ZonedDateTime dateTime) {
        return ZonedDateTime.now().isAfter(dateTime);
    }

    /**
     * @param date The {@link LocalDate} to check
     * @return True if the date is in the future, false otherwise (today would return false)
     */
    public static boolean isFuture(LocalDate date) {
        return LocalDate.now().isBefore(date);
    }

    /**
     * @param dateTime The {@link ZonedDateTime} to check
     * @return True if the date is in the future, false otherwise (right now would return false)
     */
    public static boolean isFuture(ZonedDateTime dateTime) {
        return ZonedDateTime.now().isBefore(dateTime);
    }

    /**
     * Returns the {@link ZonedDateTime} in RFC 1123 format (in UTC timezone)
     *
     * @param dateTime The {@link ZonedDateTime} instance
     * @return An RFC 1123 String representation of the date, null if the date was null
     * (UTC timezone)
     */
    public static String getRFC1123String(@Nullable ZonedDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        //Set it to the UTC time zone
        return rfcFormatter().format(dateTime.withZoneSameInstant(ZoneOffset.UTC));
    }

    /**
     * Returns the medium text of a date (Ex: Jan 1, 2000)
     *
     * @param temporal Temporal to use
     * @return The localized medium date String
     */
    public static String getMediumDateString(@NonNull Temporal temporal) {
        return mediumDateFormatter().format(temporal);
    }

    /**
     * Returns the long text of a date (Ex: January 1, 2000)
     *
     * @param temporal Temporal to use
     * @return The localized long date String
     */
    public static String getLongDateString(@NonNull Temporal temporal) {
        return longDateFormatter().format(temporal);
    }

    /**
     * Returns the short text of a time (Ex: 3:30pm)
     *
     * @param temporal Temporal to use
     * @return The localized short time String
     */
    public static String getShortTimeString(@NonNull Temporal temporal) {
        return shortTimeFormatter().format(temporal);
    }
}
