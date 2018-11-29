package com.atovi.customizablecalendar.library.model;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;

import static com.atovi.customizablecalendar.library.model.DayOfWeek.FRIDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.MONDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.SATURDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.SUNDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.THURSDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.TUESDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.WEDNESDAY;
import static com.atovi.customizablecalendar.library.model.SegmentDestination.END_MONTH;
import static com.atovi.customizablecalendar.library.model.SegmentDestination.NEXT_MONTH;
import static com.atovi.customizablecalendar.library.model.SegmentDestination.PREVIOS_MONTH;
import static com.atovi.customizablecalendar.library.model.SegmentDestination.START_MONTH;


/**
 * Created by francescofurlan on 30/06/17.
 */

@IntDef({
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
})

@Retention(RetentionPolicy.SOURCE)
public @interface DayOfWeek {
    int MONDAY = 1;
    int TUESDAY = 2;
    int WEDNESDAY = 3;
    int THURSDAY = 4;
    int FRIDAY = 5;
    int SATURDAY = 6;
    int SUNDAY = 7;
}

