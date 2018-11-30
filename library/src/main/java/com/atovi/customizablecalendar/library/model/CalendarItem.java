package com.atovi.customizablecalendar.library.model;

import org.joda.time.DateTime;

import java.util.Calendar;


public class CalendarItem {
    private long id;
    private DateTime dateTime;
    private boolean selectable;
    private boolean isBelongToThisMonth;
    private @SelectionPosition int selectionPosition = SelectionPosition.NONE;


    public CalendarItem(Calendar calendar) {
        this.dateTime = new DateTime(calendar);
        this.id = calendar.getTimeInMillis();
    }

    public CalendarItem(DateTime dateTime) {
        this.dateTime = dateTime;
        this.id = dateTime.getMillis();
    }

    public CalendarItem(int day, int month, int year) {
        this.dateTime = new DateTime(year, month, day, 0, 0);
        this.id = dateTime.getMillis();
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @CompareDateTime int compareTo(DateTime today) {
        return dateTime.compareTo(today);
    }

    public String getDayString() {
        return getDay() + "";
    }

    public int getDay() {
        return dateTime.getDayOfMonth();
    }

    public int getMonth() {
        return dateTime.getMonthOfYear();
    }

    public int getYear() {
        return dateTime.getYear();
    }

    public @DayOfWeek int getDayOfWeek() {
        return dateTime.getDayOfWeek();
    }

    public boolean isBelongToThisMonth() {
        return isBelongToThisMonth;
    }

    public void setBelongToThisMonth(boolean belongToThisMonth) {
        isBelongToThisMonth = belongToThisMonth;
    }

    public @SelectionPosition int getSelectionPosition() {
        return selectionPosition;
    }

    public void setSelectionPosition(@SelectionPosition int selectionPosition) {
        this.selectionPosition = selectionPosition;
    }

    public boolean isToday() {
        return compareTo(new DateTime().withTimeAtStartOfDay()) == CompareDateTime.SAME;
    }

    public @CompareDateTime int compareToDay() {
        return compareTo(new DateTime().withTimeAtStartOfDay());
    }
}
