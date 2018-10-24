package obautista.uga.edu.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oscar on 10/22/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StateCapitals.db";
    public static final String TABLE_NAME = "questions_table";
    public static final String COL_1 = "STATE";
    public static final String COL_2 = "CAPITAL";
    public static final String COL_3 = "SECOND_CITY";
    public static final String COL_4 = "THIRD_CITY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create quiz question table
        db.execSQL("create table " + TABLE_NAME + " (STATE TEXT PRIMARY KEY, CAPITAL TEXT, SECOND_CITY TEXT, THIRD_CITY TEXT)"  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * insertData : Method to insert a new row into the database
     * @param state The state
     * @param capitol The capitol of the state
     * @param cityOne The second largest city in the state
     * @param cityTwo The third largest city in the state
     * @return a boolean stating if the insertion was successful
     */
    public boolean insertData (String state, String capitol, String cityOne, String cityTwo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, state);
        contentValues.put(COL_2, capitol);
        contentValues.put(COL_3, cityOne);
        contentValues.put(COL_4, cityTwo);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1) return false;
        else return true;
    }

    public Cursor getAllData () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
