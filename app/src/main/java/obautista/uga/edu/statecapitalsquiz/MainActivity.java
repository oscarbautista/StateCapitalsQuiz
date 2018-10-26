package obautista.uga.edu.statecapitalsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {


    private Button quizzesBtn;
    private Button historyBtn;
    DatabaseHelper stateCapitalsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        quizzesBtn = findViewById(R.id.quizBtn);
        historyBtn = findViewById(R.id.historyBtn);

        // Initialize database and pass in values from csv file into database
        stateCapitalsDb = new DatabaseHelper(this);
        readCSV();

        // Start the quiz
        quizzesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        // Show the scores
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ScoreListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Reads in the states and their capitals from the csv file.
     * Each state has a row created in the database.
     */
    public void readCSV() {
        InputStream is = getResources().openRawResource(R.raw.state_capitals);
        BufferedReader reader = new BufferedReader (
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";

        // Read the csv file
        try {

            // Step over the headers
            reader.readLine();

            while((line = reader.readLine()) != null) {

                // Split the line and insert as a new row in the db
                String[] cells = line.split(",");
                stateCapitalsDb.insertQuestion(cells[0], cells[1], cells[2], cells[3]);
            }
        } catch(IOException e ) {
            Log.wtf("csv read", "Error reading csv file" + line, e);
            e.printStackTrace();
        }
    }

}
