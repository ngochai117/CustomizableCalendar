package com.atovi.customizablecalendar.sample;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atovi.customizablecalendar.library.adapter.WeekDaysViewAdapter;
import com.atovi.customizablecalendar.library.components.CustomizableCalendar;
import com.atovi.customizablecalendar.library.components.SubView;
import com.atovi.customizablecalendar.library.interactors.ViewInteractor;
import com.atovi.customizablecalendar.library.model.Calendar;
import com.atovi.customizablecalendar.library.model.CalendarItem;
import com.atovi.customizablecalendar.library.model.SegmentDestination;
import com.atovi.customizablecalendar.library.model.SelectionPosition;
import com.atovi.customizablecalendar.library.utils.DateUtils;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by francescofurlan on 03/07/17.
 */

public class CalendarViewInteractor implements ViewInteractor {
    private static final String TAG = "CalendarViewInteractor";
    private Context context;
    private Calendar calendar;
    private TextView firstDaySelectedTxt;
    private TextView lastDaySelectedTxt;
    private CustomizableCalendar customizableCalendar;
    private TextView tvMonth;

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
        Log.d(TAG, "onHeaderBindView: ");
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
        holder.weekDayTxt.setTextColor(context.getResources().getColor(android.R.color.holo_green_light));
    }

    @Override
    public void onSubViewBindView(View view, DateTime currentMonth) {
        Log.d(getClass().getSimpleName(), "onSubViewBindView: ");
        tvMonth = view.findViewById(android.R.id.message);
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
        Log.d(TAG, "onCalendarBindView: ");
    }

    @Override
    public void onMonthBindView(View view) {
        Log.d(TAG, "onMonthBindView: ");
    }

    @Override
    public View onMonthCellBindView(View view, CalendarItem currentItem) {

        final TextView dayView = view.findViewById(android.R.id.title);
        final View startSelectionView = view.findViewById(android.R.id.startSelectingText);
        final View endSelectionView = view.findViewById(android.R.id.stopSelectingText);

        startSelectionView.setVisibility(View.GONE);
        endSelectionView.setVisibility(View.GONE);

        if (currentItem == null) {
            dayView.setBackground(null);
            dayView.setText(null);
        } else if (!currentItem.isBelongToThisMonth()) {
            dayView.setBackground(null);
            dayView.setText(null);
        } else {
            currentItem.setSelectable(true);
            dayView.setAlpha(1f);
            Integer backgroundResource = null;
            if (currentItem.isToday()) {
                backgroundResource = com.atovi.customizablecalendar.library.R.drawable.empty_circle;
            }
            switch (currentItem.getSelectionPosition()) {
                case SelectionPosition.START:
                    backgroundResource = com.atovi.customizablecalendar.library.R.drawable.left_rounded_rectangle;
                    endSelectionView.setVisibility(View.VISIBLE);
                    break;
                case SelectionPosition.MIDDLE:
                    backgroundResource = com.atovi.customizablecalendar.library.R.drawable.rectangle;
                    startSelectionView.setVisibility(View.VISIBLE);
                    endSelectionView.setVisibility(View.VISIBLE);
                    break;
                case SelectionPosition.END:
                    backgroundResource = com.atovi.customizablecalendar.library.R.drawable.right_rounded_rectangle;
                    startSelectionView.setVisibility(View.VISIBLE);
                    break;
                case SelectionPosition.FULL:
                    backgroundResource = com.atovi.customizablecalendar.library.R.drawable.circle;
                    break;
                case SelectionPosition.NONE:
                    break;
            }

            int color = Color.BLACK;
            if (backgroundResource != null) {
                dayView.setBackgroundResource(backgroundResource);
                if (calendar.isMultipleSelectionEnabled() && backgroundResource != com.atovi.customizablecalendar.library.R.drawable.empty_circle) {
                    color = Color.WHITE;
                }
            } else {
                dayView.setBackground(null);
            }

            dayView.setTextColor(color);
            dayView.setText(currentItem.getDayString());
        }
        return view;
    }

    @Override
    public boolean hasImplementedDayCalculation() {
        return false;
    }

    @Override
    public List<CalendarItem> calculateDays(int year, int month, int firstDayOfMonth, int lastDayOfMonth, DateTime monthDate) {
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
        return true;
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

    @Override
    public void onCurrentMonthChanged(DateTime currentMonth) {
        tvMonth.setText(currentMonth.toString("MMMM yyyy", Locale.getDefault()));
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
