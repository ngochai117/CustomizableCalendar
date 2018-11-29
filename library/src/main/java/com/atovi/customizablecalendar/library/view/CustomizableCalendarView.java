package com.atovi.customizablecalendar.library.view;

import org.joda.time.DateTime;

/**
 * Created by francescofurlan on 23/06/17.
 */

public interface CustomizableCalendarView extends BaseView {
    void onCurrentMonthChanged(DateTime currentMonth);
}
