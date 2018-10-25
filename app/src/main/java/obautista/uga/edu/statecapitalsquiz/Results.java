package obautista.uga.edu.statecapitalsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Results extends AppCompatActivity {

    private TextView scoreText;
    private TextView correctText;
    private TextView incorrectText;
    private Button homeBtn;
    DatabaseHelper stateCapitalsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Calculate score
        int correct = getIntent().getIntExtra("totalCorrect", 0);
        int incorrect = 6 - correct;
        double score = (double) correct * 100 / 6.0;
        DecimalFormat nf = new DecimalFormat("0.##");

        // Get current date
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());

        // Initialize views and database object
        scoreText = findViewById(R.id.score);
        correctText = findViewById(R.id.correct);
        incorrectText = findViewById(R.id.incorrect);
        homeBtn = findViewById(R.id.homeBtn);
        stateCapitalsDb = new DatabaseHelper(this);

        // Set text for views
        scoreText.setText(nf.format(score) + "%");
        correctText.setText(Integer.toString(correct));
        incorrectText.setText(Integer.toString(incorrect));

        // Insert score into the database
        stateCapitalsDb.insertScore (date, correct, incorrect, score);

        // Go back to MainActivity (Splash Screen)
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
