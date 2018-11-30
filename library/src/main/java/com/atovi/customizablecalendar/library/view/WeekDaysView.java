package com.atovi.customizablecalendar.library.view;

import com.atovi.customizablecalendar.library.model.DayOfWeek;

/**
 * Created by francescofurlan on 30/06/17.
 */

public interface WeekDaysView extends BaseView {
    void onFirstDayOfWeek(@DayOfWeek int firstDayOfWeek);
}
