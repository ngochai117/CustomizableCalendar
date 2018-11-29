package com.atovi.customizablecalendar.library.view;

import org.joda.time.DateTime;

/**
 * Created by francescofurlan on 30/06/17.
 */

public interface SubView extends BaseView {
    void onMonthChanged(DateTime month);
}
