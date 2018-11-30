package com.atovi.customizablecalendar.library.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.atovi.customizablecalendar.library.model.CompareDateTime.AFTER;
import static com.atovi.customizablecalendar.library.model.CompareDateTime.BEFORE;
import static com.atovi.customizablecalendar.library.model.CompareDateTime.SAME;


@IntDef({
        SAME,
        AFTER,
        BEFORE
})

@Retention(RetentionPolicy.SOURCE)
public @interface CompareDateTime {
    int SAME = 0;
    int AFTER= 1;
    int BEFORE = -1;
}

