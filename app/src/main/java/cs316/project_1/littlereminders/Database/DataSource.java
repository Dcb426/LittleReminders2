package cs316.project_1.littlereminders.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mdatabase;
    private SQLiteOpenHelper mdbHelper;

    public DataSource(Context context) {

        this.mContext = context;
        mdbHelper = new DBhelper(mContext);
        mdatabase = mdbHelper.getWritableDatabase();
    }

    public void open(){
        mdatabase = mdbHelper.getWritableDatabase();
    }

    public void wipe() { mdbHelper.onUpgrade(mdatabase,0,0);}

    public void close(){
        mdbHelper.close();
    }

    public DataStudent createStudent(DataStudent student){
        ContentValues values = student.toValues();
        mdatabase.insert(StudentsTable.TABLE_STUDENT,null,values);
        return student;
    }

    public List<DataStudent> getAll(){
        List<DataStudent> dataStudents = new ArrayList<>();
        Cursor cursor = mdatabase.query(StudentsTable.TABLE_STUDENT, StudentsTable.ALL_COL,

                null,null,null,null,"UPPER("+StudentsTable.COLUMN_TITLE+")");

        while(cursor.moveToNext()){
            DataStudent student = new DataStudent();
            student.setID(cursor.getString(cursor.getColumnIndex(StudentsTable.COLUMN_ID)));
            student.setstudentTitle(cursor.getString(cursor.getColumnIndex(StudentsTable.COLUMN_TITLE)));
            student.setstudentText(cursor.getString(cursor.getColumnIndex(StudentsTable.COLUMN_TEXT)));
            student.setAmount(cursor.getColumnIndex(StudentsTable.COLUMN_AMOUNT));
            student.setTime(cursor.getColumnIndex(StudentsTable.COLUMN_TIME));
            dataStudents.add(student);
        }
        cursor.close();
        return dataStudents;
    }

    public long getdataItemCount() {
        return DatabaseUtils.queryNumEntries(mdatabase, StudentsTable.TABLE_STUDENT);
    }

    private void toastMessage(String message){
        Toast.makeText(mContext,message, Toast.LENGTH_SHORT).show();
    }

}
