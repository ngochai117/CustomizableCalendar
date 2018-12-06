package com.atovi.customizablecalendar.library.interactors;

import android.view.View;
import android.view.ViewGroup;

import com.atovi.customizablecalendar.library.adapter.WeekDaysViewAdapter;
import com.atovi.customizablecalendar.library.model.CalendarItem;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by francescofurlan on 26/06/17.
 */

public interface ViewInteractor {
    void onCustomizableCalendarBindView(View view);

    void onHeaderBindView(ViewGroup view);

    void onWeekDaysBindView(View view);

    void onWeekDayBindView(WeekDaysViewAdapter.WeekDayVH holder, String weekDay);

    void onSubViewBindView(View view, DateTime month);

    void onCalendarBindView(View view);

    void onMonthBindView(View view);

    View onMonthCellBindView(View view, CalendarItem currentItem);

    boolean hasImplementedDayCalculation();

    List<CalendarItem> calculateDays(int year, int month, int firstDayOfMonth, int lastDayOfMonth, DateTime monthDate);

    boolean hasImplementedSelection();

    int setSelected(boolean multipleSelection, DateTime dateSelected);

    boolean hasImplementedMonthCellBinding();

    View getMonthGridView(View rootView);

    boolean hasImplementedWeekDayNameFormat();

    String formatWeekDayName(String nameOfDay);

    void onCurrentMonthChanged(DateTime currentMonth);
}
