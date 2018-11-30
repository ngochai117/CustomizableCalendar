package com.atovi.customizablecalendar.library.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atovi.customizablecalendar.library.R;
import com.atovi.customizablecalendar.library.interactors.AUCalendar;
import com.atovi.customizablecalendar.library.interactors.ViewInteractor;
import com.atovi.customizablecalendar.library.presenter.interfeaces.CustomizableCalendarPresenter;

import org.joda.time.DateTime;

import java.util.Locale;

/**
 * Created by francescofurlan on 23/06/17.
 */

public class SubView extends RelativeLayout implements com.atovi.customizablecalendar.library.view.SubView {
    TextView monthTxt;

    private ViewInteractor viewInteractor;

    private @LayoutRes
    int layoutResId = R.layout.sub_view;
    private CustomizableCalendarPresenter presenter;
    private Context context;

    public SubView(Context context) {
        this(context, null);
    }

    public SubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomizableCalendar);
        if (typedArray != null) {
            layoutResId = typedArray.getResourceId(R.styleable.CustomizableCalendar_layout, R.layout.sub_view);
            typedArray.recycle();
        }

        LayoutInflater.from(context).inflate(layoutResId, this);
        monthTxt = (TextView) findViewById(android.R.id.message);
    }

    @Override
    public void onMonthChanged(DateTime currentMonth) {
        Log.d(getClass().getSimpleName(), "onCurrentMonthChange: ");
        if (viewInteractor != null) {
            viewInteractor.onCurrentMonthChanged(currentMonth);
        } else {
            monthTxt.setText(currentMonth.toString("MMMMM", Locale.getDefault()));
        }
    }

    @Override
    public void refreshData() {
    }

    @Override
    public void setLayoutResId(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
    }

    @Override
    public void injectViewInteractor(ViewInteractor viewInteractor) {
        this.viewInteractor = viewInteractor;
        DateTime firstMonth = AUCalendar.getInstance().getFirstMonth();
        if (viewInteractor != null) {
            viewInteractor.onSubViewBindView(this, firstMonth);
            onMonthChanged(firstMonth);
        }
    }

    @Override
    public void injectPresenter(CustomizableCalendarPresenter presenter) {
        this.presenter = presenter;
        this.presenter.injectSubView(this);
    }
}