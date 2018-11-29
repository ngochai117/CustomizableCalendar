package com.atovi.customizablecalendar.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atovi.customizablecalendar.library.adapter.WeekDaysViewAdapter;
import com.atovi.customizablecalendar.library.components.CustomizableCalendar;
import com.atovi.customizablecalendar.library.interactors.ViewInteractor;
import com.atovi.customizablecalendar.library.model.Calendar;
import com.atovi.customizablecalendar.library.model.CalendarItem;
import com.atovi.customizablecalendar.library.model.SegmentDestination;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by francescofurlan on 03/07/17.
 */

public class CalendarViewInteractor implements ViewInteractor {
    private Context context;
    private Calendar calendar;
    private TextView firstDaySelectedTxt;
    private TextView lastDaySelectedTxt;
    private CustomizableCalendar customizableCalendar;
    CalendarViewInteractor(Context context) {
        this.context = context;
    }

    @Override
    public void onCustomizableCalendarBindView(View view) {
        try {
            customizableCalendar = (CustomizableCalendar) view;
        } catch (ClassCastException e) {
            throw new ClassCastException("view not is CustomizableCalendar");
        }
    }

    @Override
    public void onHeaderBindView(ViewGroup view) {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.calendar_header, view);
        firstDaySelectedTxt = (TextView) layout.findViewById(R.id.first_day_selected);
        lastDaySelectedTxt = (TextView) layout.findViewById(R.id.last_day_selected);
        updateCalendar(calendar);
    }

    @Override
    public void onWeekDaysBindView(View view) {

    }

    @Override
    public void onWeekDayBindView(WeekDaysViewAdapter.WeekDayVH holder, String weekDay) {

    }

    @Override
    public void onSubViewBindView(View view, String month) {
        view.findViewById(R.id.imgVNavLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizableCalendar.scrollToSegment(SegmentDestination.PREVIOS_MONTH);
            }
        });

        view.findViewById(R.id.imgVNavRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizableCalendar.scrollToSegment(SegmentDestination.NEXT_MONTH);
            }
        });
    }

    @Override
    public void onCalendarBindView(View view) {

    }

    @Override
    public void onMonthBindView(View view) {

    }

    @Override
    public View onMonthCellBindView(View view, CalendarItem currentItem) {
        return null;
    }

    @Override
    public boolean hasImplementedDayCalculation() {
        return false;
    }

    @Override
    public List<CalendarItem> calculateDays(int year, int month, int firstDayOfMonth, int lastDayOfMonth) {
        return null;
    }

    @Override
    public boolean hasImplementedSelection() {
        return false;
    }

    @Override
    public int setSelected(boolean multipleSelection, DateTime dateSelected) {
        return -1;
    }

    @Override
    public boolean hasImplementedMonthCellBinding() {
        return false;
    }

    @Override
    public View getMonthGridView(View rootView) {
        return null;
    }

    @Override
    public boolean hasImplementedWeekDayNameFormat() {
        return false;
    }

    @Override
    public String formatWeekDayName(String nameOfDay) {
        return null;
    }

    void updateCalendar(Calendar calendar) {
        this.calendar = calendar;
        if (firstDaySelectedTxt != null && lastDaySelectedTxt != null) {
            DateTime firstDate = calendar.getFirstSelectedDay();
            DateTime lastDate = calendar.getLastSelectedDay();
            if (firstDate != null) {
                firstDaySelectedTxt.setText(firstDate.toString("dd MMMMM yyyy"));
            }
            if (lastDate != null) {
                lastDaySelectedTxt.setText(lastDate.toString("dd MMMMM yyyy"));
            }
        }
    }
}
