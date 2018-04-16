package cs316.project_1.littlereminders;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_AMOUNTREMIND;
import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_ID;
import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_TEXT;
import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_TIMESPERDAY;
import static cs316.project_1.littlereminders.MyDBhandler.COLUMN_TITLE;
import static cs316.project_1.littlereminders.MyDBhandler.TABLE_NAME;

public class DataSource2 {

    private Context mContext;

    private SQLiteDatabase mdatabase;

    private SQLiteOpenHelper mdbHelper;

    public static final String[] ALL_COL = {

            COLUMN_ID,COLUMN_TITLE,COLUMN_TEXT,COLUMN_AMOUNTREMIND,COLUMN_TIMESPERDAY};



    public DataSource2(Context context) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        String dro = currentUser.getEmail();
        final String[] email = dro.split("@");

        this.mContext = context;

        mdbHelper = new MyDBhandler(mContext, email[0]);

        mdatabase = mdbHelper.getWritableDatabase();

    }

    public void open(){

        mdatabase = mdbHelper.getWritableDatabase();

    }

    public void wipe() { mdbHelper.onUpgrade(mdatabase,0,0);}

    public void close(){
        mdbHelper.close();

    }

    /*public DataStudent createStudent(DataStudent student){

        ContentValues values = student.toValues();

        mdatabase.insert(StudentsTable.TABLE_STUDENT,null,values);

        return student;

    }*/

    public void addHandler(ContentValues value)
    {
        ContentValues values = value;
        mdatabase.insert(TABLE_NAME, null, values);
    }



    public List<setNotification> getall()
    {

        Cursor cursor = mdatabase.query(TABLE_NAME, ALL_COL,

                null,null,null,null,null);
        List<setNotification> datanotes = new ArrayList<>();
        while(cursor.moveToNext())
        {
            setNotification note = new setNotification();
            note.setID(Integer.parseInt(cursor.getString(0)));
            note.setnotificationTitle(cursor.getString(1));
            note.setnotificationText(cursor.getString(2));
            note.setnotificationAmountRemind(Integer.parseInt(cursor.getString(3)));
            note.setnotificationTimesPerDay(Integer.parseInt(cursor.getString(4)));
            // Adding contact to list
            datanotes.add(note);
        }
        cursor.close();
        return datanotes;
    }


    public long getdataItemCount() {

        return DatabaseUtils.queryNumEntries(mdatabase, MyDBhandler.TABLE_NAME);

    }

}