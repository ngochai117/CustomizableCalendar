package com.atovi.customizablecalendar.library.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.atovi.customizablecalendar.library.model.SegmentDestination.END_MONTH;
import static com.atovi.customizablecalendar.library.model.SegmentDestination.NEXT_MONTH;
import static com.atovi.customizablecalendar.library.model.SegmentDestination.PREVIOS_MONTH;
import static com.atovi.customizablecalendar.library.model.SegmentDestination.START_MONTH;


/**
 * Created by francescofurlan on 30/06/17.
 */

@StringDef({
        NEXT_MONTH,
        PREVIOS_MONTH,
        START_MONTH,
        END_MONTH
})

@Retention(RetentionPolicy.SOURCE)
public @interface SegmentDestination {
    String NEXT_MONTH = "nextMonth";
    String PREVIOS_MONTH = "previosMonth";
    String START_MONTH = "startMonth";
    String END_MONTH = "endMonth";
}

