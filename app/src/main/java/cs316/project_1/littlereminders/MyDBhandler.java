package cs316.project_1.littlereminders;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBhandler extends SQLiteOpenHelper {

    //information of database
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Notifications";
    static final String COLUMN_ID = "notificationID";
    static final String COLUMN_TITLE = "notificationTitle";
    static final String COLUMN_TEXT = "notificationText";
    static final String COLUMN_AMOUNTREMIND = "notificationAmountRemind";
    static final String COLUMN_TIMESPERDAY = "notificationTimesPerDay";

    //initialize the database
    MyDBhandler(Context context, String name) {

        super(context,  name + ".db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("

                    + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE + "TEXT,"

                    + COLUMN_TEXT + "TEXT," + COLUMN_AMOUNTREMIND + "INTEGER," + COLUMN_TIMESPERDAY + "INTEGER" + ");";

            db.execSQL(CREATE_CONTACTS_TABLE);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Creating tables again
        onCreate(db);
    }
}
