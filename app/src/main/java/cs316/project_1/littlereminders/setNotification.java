package cs316.project_1.littlereminders;

import android.content.ContentValues;

import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_AMOUNTREMIND;
import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_ID;
import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_TEXT;
import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_TIMESPERDAY;
import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_TITLE;

public class setNotification {

    private int notificationID;
    private String notificationTitle = "";
    private String notificationText = "";
    private int notificationAmountRemind;
    private int notificationTimesPerDay;

    // constructors
    setNotification() {}
    public setNotification(int id, String title, String text, int amount, int times) {

        this.notificationID = id;
        this.notificationTitle =  title;
        this.notificationText = text;
        this.notificationAmountRemind = amount;
        this.notificationTimesPerDay = times;
    }

    // properties
    public void setID(int id) {
        this.notificationID = id;
    }
    public int getID() {
        return this.notificationID;
    }

    public void setnotificationTitle(String title) {
        this.notificationTitle = title;
    }

    public String getnotificationTitle() {
        return this.notificationTitle;
    }

    public void setnotificationText(String text) {
        this.notificationText = text;
    }

    public String getnotificationText() {
        return this.notificationText;
    }

    public void setnotificationAmountRemind(int text) {
        this.notificationAmountRemind = text;
    }

    public int getnotificationAmountRemind() {
        return this.notificationAmountRemind;
    }

    public void setnotificationTimesPerDay(int text) {
        this.notificationTimesPerDay = text;
    }

    public int getnotificationTimesPerDay() {
        return this.notificationTimesPerDay;
    }

    public ContentValues noteadd()
    {
        ContentValues values = new ContentValues(6);
        values.put(COLUMN_ID, notificationID);
        values.put(COLUMN_TITLE, notificationTitle);
        values.put(COLUMN_TEXT, notificationText);
        values.put(COLUMN_AMOUNTREMIND, notificationAmountRemind);
        values.put(COLUMN_TIMESPERDAY, notificationTimesPerDay);
        return values;
    }
}
