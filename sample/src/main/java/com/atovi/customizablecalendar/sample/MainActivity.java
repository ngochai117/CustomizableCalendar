package com.atovi.customizablecalendar.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.atovi.customizablecalendar.library.components.CustomizableCalendar;
import com.atovi.customizablecalendar.library.interactors.AUCalendar;
import com.atovi.customizablecalendar.library.model.Calendar;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_month)
    CustomizableCalendar customizableCalendar;

    private CompositeDisposable subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        subscriptions = new CompositeDisposable();
        updateData();
    }

    private void updateData() {
        // setting up first and last month that must be showed in the calendar
        DateTime firstMonth = new DateTime().withDayOfMonth(1);
        DateTime lastMonth = new DateTime().plusMonths(24).withDayOfMonth(1);

        // create the Calendar obj and setting it up with some configs like:
        // - first selected day
        // - last selected day
        // - multiple selection
        final Calendar calendar = new Calendar(firstMonth, lastMonth);

        calendar.setMultipleSelection(true);

        // create a CalendarViewInteractor obj needed to interact with the CustomizableCalendar
        final CalendarViewInteractor calendarViewInteractor = new CalendarViewInteractor(getBaseContext());

        // create the AUCalendar obj and observes changes on it
        AUCalendar auCalendar = AUCalendar.getInstance(calendar);
        calendarViewInteractor.updateCalendar(calendar);
        subscriptions.add(
                auCalendar.observeChangesOnCalendar()
                        .subscribe(changeSet -> calendarViewInteractor.updateCalendar(calendar))
        );

        // inject (set) the calendarViewInteractor to the CustomizableCalendar
        customizableCalendar.injectViewInteractor(calendarViewInteractor);

    }
}