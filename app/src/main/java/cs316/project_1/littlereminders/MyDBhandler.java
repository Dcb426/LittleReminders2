package cs316.project_1.littlereminders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MyDBhandler extends SQLiteOpenHelper {

    //information of database
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Notifications";
    public static final String COLUMN_ID = "notificationID";
    public static final String COLUMN_TITLE = "notificationTitle";
    public static final String COLUMN_TEXT = "notificationText";

    //initialize the database
    public MyDBhandler(Context context, FirebaseUser currentuser, String name) {
        super(context, name + ".db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("

                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE + " TEXT,"

                + COLUMN_TEXT + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Creating tables again
        onCreate(db);
    }

    public void addHandler(setNotification notification)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, notification.getnotificationTitle());
        values.put(COLUMN_TEXT, notification.getnotificationText());
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public List<setNotification> getAllShops() {
        List<setNotification> noteList = new ArrayList<setNotification>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                setNotification note = new setNotification();
                note.setID(Integer.parseInt(cursor.getString(0)));
                note.setnotificationTitle(cursor.getString(1));
                note.setnotificationText(cursor.getString(2));
                // Adding contact to list
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return noteList;
    }


    public int updateHandler(setNotification note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, note.getnotificationTitle());
        values.put(COLUMN_TEXT, note.getnotificationText());
        // updating row
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getID())});
    }


    public void deleteHandler(setNotification notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[] { String.valueOf(notification.getID()) });
        db.close();
    }
}
