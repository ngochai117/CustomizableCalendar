package com.atovi.customizablecalendar.library.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


import static com.atovi.customizablecalendar.library.model.SelectionPosition.END;
import static com.atovi.customizablecalendar.library.model.SelectionPosition.FULL;
import static com.atovi.customizablecalendar.library.model.SelectionPosition.MIDDLE;
import static com.atovi.customizablecalendar.library.model.SelectionPosition.NONE;
import static com.atovi.customizablecalendar.library.model.SelectionPosition.START;


@IntDef({
        START,
        MIDDLE,
        END,
        FULL,
        NONE
})

@Retention(RetentionPolicy.SOURCE)
public @interface SelectionPosition {
    int START = 0;
    int MIDDLE = 1;
    int END = 2;
    int FULL = 3;
    int NONE = 4;
}

