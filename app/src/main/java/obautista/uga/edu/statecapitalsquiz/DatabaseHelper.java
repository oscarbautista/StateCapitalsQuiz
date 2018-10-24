package obautista.uga.edu.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Oscar on 10/22/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StateCapitals.db";
    public static final String TABLE_QUESTIONS = "questions_table";
    public static final String STATE = "STATE";
    public static final String CAPITAL = "CAPITAL";
    public static final String SECOND_CITY = "SECOND_CITY";
    public static final String THIRD_CITY = "THIRD_CITY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create quiz question table
        db.execSQL("create table " + TABLE_QUESTIONS + " (STATE TEXT PRIMARY KEY, CAPITAL TEXT, SECOND_CITY TEXT, THIRD_CITY TEXT)"  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    /**
     * insertQuestion : Method to insert a new row into the database
     * @param state The state
     * @param capital The capitol of the state
     * @param secondCity The second largest city in the state
     * @param thirdCity The third largest city in the state
     * @return a boolean stating if the insertion was successful
     */
    public boolean insertQuestion (String state, String capital, String secondCity, String thirdCity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STATE, state);
        contentValues.put(CAPITAL, capital);
        contentValues.put(SECOND_CITY, secondCity);
        contentValues.put(THIRD_CITY, thirdCity);

        long result = db.insert(TABLE_QUESTIONS, null, contentValues);

        if(result == -1) return false;
        else return true;
    }

    /**
     * getSixQuestions : Pulls six random questions from the db and stores them as QuizQuestion objects
     * @return QuizQuestion array with the six QuizQuestion objects
     */
    public QuizQuestion[] getSixQuestions () {

        // Pull six random questions from the database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_QUESTIONS + " ORDER BY RANDOM() LIMIT 6", null);

        QuizQuestion[] sixQuestions = new QuizQuestion[6];

        // If no data is in the db
        if(res.getCount() == 0) {
            Log.w("Error", "No data found in database");
            return sixQuestions;
        }

        // Store the six questions in the array
        int count = 0;
        while(res.moveToNext()) {
            QuizQuestion question = new QuizQuestion
                    (res.getString(0), res.getString(1), res.getString(2), res.getString(3));

            sixQuestions[count] = question;
            count ++;
        }

        return sixQuestions;
    }

}
