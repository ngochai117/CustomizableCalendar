package com.atovi.customizablecalendar.library.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.atovi.customizablecalendar.library.R;
import com.atovi.customizablecalendar.library.adapter.CalendarViewAdapter;
import com.atovi.customizablecalendar.library.interactors.AUCalendar;
import com.atovi.customizablecalendar.library.interactors.ViewInteractor;
import com.atovi.customizablecalendar.library.model.SegmentDestination;
import com.atovi.customizablecalendar.library.presenter.interfeaces.CustomizableCalendarPresenter;
import com.atovi.customizablecalendar.library.view.CalendarView;

import org.joda.time.DateTime;

/**
 * Created by francescofurlan on 23/06/17.
 */

public class CalendarRecyclerView extends RecyclerView implements CalendarView {
    private CalendarViewAdapter calendarViewAdapter;
    private ViewInteractor viewInteractor;
    private Context context;
    private CustomizableCalendarPresenter presenter;
    private AUCalendar calendar;
    private
    @LayoutRes
    int monthResId = R.layout.calendar_view;
    private
    @LayoutRes
    int monthCellResId = R.layout.calendar_cell;

    public CalendarRecyclerView(Context context) {
        this(context, null);
    }

    public CalendarRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomizableCalendar);
        if (typedArray != null) {
            monthResId = typedArray.getResourceId(R.styleable.CustomizableCalendar_month_layout, R.layout.calendar_view);
            monthCellResId = typedArray.getResourceId(R.styleable.CustomizableCalendar_cell_layout, R.layout.calendar_cell);
            typedArray.recycle();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        this.calendar = AUCalendar.getInstance();
    }

    @Override
    public void refreshData() {
        calendarViewAdapter.refreshData();
    }

    @Override
    public void setLayoutResId(@LayoutRes int layoutResId) {

    }

    @Override
    public void injectViewInteractor(ViewInteractor viewInteractor) {
        this.viewInteractor = viewInteractor;
        if (viewInteractor != null) {
            viewInteractor.onCalendarBindView(this);
        }
        setupCalendarAdapter();
        setupCalendarScroll();
    }

    @Override
    public void injectPresenter(CustomizableCalendarPresenter presenter) {
        this.presenter = presenter;
        this.presenter.injectCalendarView(this);
    }

    private void setupCalendarAdapter() {
        calendarViewAdapter = new CalendarViewAdapter(context);
        calendarViewAdapter.injectViewInteractor(viewInteractor);
        calendarViewAdapter.setLayoutResId(monthResId);
        calendarViewAdapter.setDayLayoutResId(monthCellResId);
        setAdapter(calendarViewAdapter);
    }

    private void setupCalendarScroll() {
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(this);
        addOnChildAttachStateChangeListener(new OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                MonthGridView monthGridView = (MonthGridView) view;
                monthGridView.subscribe();
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                MonthGridView monthGridView = (MonthGridView) view;
                monthGridView.unsubscribe();
            }
        });

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case SCROLL_STATE_IDLE: {
                        View view = snapHelper.findSnapView(getLayoutManager());
                        if (view != null) {
                            int currentPosition = getChildAdapterPosition(view);
                            DateTime currentMonth = calendar.getMonths().get(currentPosition);
                            calendar.setCurrentMonth(currentMonth);
                        }
                    }
                }
            }
        });
    }

    private int getPositionOfDate(DateTime dateTime) {
        return calendar.getMonths().indexOf(dateTime);
    }

    public void scrollToDate(DateTime dateTime) {
        scrollToDate(dateTime, true);
    }

    public void scrollToDate(DateTime dateTime, boolean animateScroll) {
        dateTime = dateTime.withTimeAtStartOfDay().withDayOfMonth(1);
        int positionDes = getPositionOfDate(dateTime);
        if (positionDes != -1 && dateTime != calendar.getCurrentMonth()) {
            if (animateScroll) {
                smoothScrollToPosition(positionDes);
            } else {
                scrollToPosition(positionDes);
            }
            calendar.setCurrentMonth(dateTime);
        }
    }

    public void scrollToSegment(@SegmentDestination String segmentDestination) {
        scrollToSegment(segmentDestination, true);
    }

    public void scrollToSegment(@SegmentDestination String segmentDestination, boolean animateScroll) {
        switch (segmentDestination) {
            case SegmentDestination.NEXT_MONTH:
                scrollToDate(calendar.getCurrentMonth().plusMonths(1), animateScroll);
                break;
            case SegmentDestination.PREVIOS_MONTH:
                scrollToDate(calendar.getCurrentMonth().minusMonths(1), animateScroll);
                break;
            case SegmentDestination.START_MONTH:
                scrollToDate(calendar.getFirstMonth(), animateScroll);
                break;
            case SegmentDestination.END_MONTH:
                scrollToDate(calendar.getLastMonth(), animateScroll);
                break;
            default:
                break;
        }
    }

    @Override
    public void setMonthLayoutResId(@LayoutRes int layoutResId) {
        calendarViewAdapter.setLayoutResId(layoutResId);
    }

    @Override
    public void setDayLayoutResId(@LayoutRes int layoutResId) {
        calendarViewAdapter.setDayLayoutResId(layoutResId);
    }
}