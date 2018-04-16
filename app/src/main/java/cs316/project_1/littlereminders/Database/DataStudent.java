package cs316.project_1.littlereminders.Database;

import android.content.ContentValues;


public class DataStudent {

    private String studentid;
    private String studentTitle;
    private String studentText;
    private int Amount;
    private int Time;

    public DataStudent(){
    }

    //Setters
    public void setID(String id) {
        this.studentid = id;
    }

    public void setstudentTitle(String n) {
        this.studentTitle = n;
    }

    public void setstudentText(String n) {
        this.studentText = n;
    }

    public void setAmount(int m) {
        this.Amount = m;
    }

    public void setTime(int h) {
        this.Time = h;
    }
    

    //public void setImage(Bitmap c) {this.image=c;}



    //Getters

    public String getID() {
        return this.studentid;
    }

    public String getstudentTitle() {
        return this.studentTitle;
    }

    public String getstudentText() {
        return this.studentText;
    }

    public int getAmount() {
        return this.Amount;
    }

    public int getTime() {
        return this.Time;
    }




    public ContentValues toValues()
    {
        ContentValues val = new ContentValues(5);
        val.put(StudentsTable.COLUMN_ID,studentid);
        val.put(StudentsTable.COLUMN_TITLE, studentTitle);
        val.put(StudentsTable.COLUMN_TEXT, studentText);
        val.put(StudentsTable.COLUMN_AMOUNT, Amount);
        val.put(StudentsTable.COLUMN_TIME, Time);
        return val;
    }

}

