package obautista.uga.edu.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Oscar on 10/22/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StateCapitals.db";
    private static final String TABLE_QUESTIONS = "questions_table";
    private static final String STATE = "state";
    private static final String CAPITAL = "capital";
    private static final String SECOND_CITY = "second_city";
    private static final String THIRD_CITY = "third_city";
    private static final String TABLE_SCORES = "scores_table";
    private static final String QUIZ_ID = "quiz_id";
    private static final String DATE = "date";
    private static final String CORRECT_NUM = "correct_num";
    private static final String INCORRECT_NUM = "incorrect_num";
    private static final String SCORE = "score";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create quiz question table
        db.execSQL("create table " + TABLE_QUESTIONS + " (STATE TEXT PRIMARY KEY, CAPITAL TEXT, SECOND_CITY TEXT, THIRD_CITY TEXT)"  );
        db.execSQL("create table " + TABLE_SCORES + " (TEST_ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, CORRECT_NUM INTEGER, INCORRECT_NUM INTEGER, SCORE DOUBLE)"  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    /**
     * insertQuestion : Method to insert a new question into the database
     * @param state The state
     * @param capital The capitol of the state
     * @param secondCity The second largest city in the state
     * @param thirdCity The third largest city in the state
     * @return a boolean stating if the insertion was successful
     */
    public boolean insertQuestion (String state, String capital, String secondCity, String thirdCity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(STATE, state);
        cv.put(CAPITAL, capital);
        cv.put(SECOND_CITY, secondCity);
        cv.put(THIRD_CITY, thirdCity);

        long result = db.insert(TABLE_QUESTIONS, null, cv);

        if(result == -1) return false;
        else return true;
    }

    /**
     * insertScore : Method to insert a new quiz result into the database
     * @param date The date the quiz was completed
     * @param correctNum The number of correct answers
     * @param incorrectNum The number of incorrect answers
     * @param score The score percentage out of 100
     * @return a boolean stating if the insertion was successful
     */
    public boolean insertScore (String date, int correctNum, int incorrectNum, double score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(CORRECT_NUM, correctNum);
        cv.put(INCORRECT_NUM, incorrectNum);
        cv.put(SCORE, score);

        long result = db.insert(TABLE_SCORES, null, cv);

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

    public ArrayList<ScoreCard> allScores () {

        // Pull all scores from the database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_SCORES, null);

        ArrayList<ScoreCard> scores = new ArrayList<>();

        // If no data is in the db
        if(res.getCount() == 0) {
            Log.w("Error", "No data found in database");
            return scores;
        }

        // Store all the scores in the array list
        while(res.moveToNext()) {
            ScoreCard score = new ScoreCard
                    (res.getString(1), res.getDouble(4), res.getInt(2), res.getInt(3));

            scores.add(score);
        }

        return scores;

    }

}
