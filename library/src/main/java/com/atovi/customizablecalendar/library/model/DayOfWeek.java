package com.atovi.customizablecalendar.library.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.atovi.customizablecalendar.library.model.DayOfWeek.FRIDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.MONDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.SATURDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.SUNDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.THURSDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.TUESDAY;
import static com.atovi.customizablecalendar.library.model.DayOfWeek.WEDNESDAY;


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

