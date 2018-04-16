package cs316.project_1.littlereminders.Database;

public class StudentsTable {

    public static final String TABLE_STUDENT = "dent";
    public static final String COLUMN_ID = "studentId";
    public static final String COLUMN_TITLE = "studentTitle";
    public static final String COLUMN_TEXT = "studentText";
    public static final String COLUMN_AMOUNT = "Amount";
    public static final String COLUMN_TIME = "Time";


    public static final String[] ALL_COL = {

            COLUMN_ID,COLUMN_TITLE,COLUMN_TEXT,COLUMN_AMOUNT,COLUMN_TIME};

    public static final String SQL_CREATE =

            "CREATE TABLE " + TABLE_STUDENT + " (" +

                    COLUMN_ID + " TEXT PRIMARY KEY," +

                    COLUMN_TITLE + " TEXT," +

                    COLUMN_TEXT + " TEXT," +

                    COLUMN_AMOUNT + " INTEGER," +

                    COLUMN_TIME + " INTEGER)";

    public static final String SQL_DELETE =

            "DROP TABLE " + TABLE_STUDENT;

}