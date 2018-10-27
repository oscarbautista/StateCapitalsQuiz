package obautista.uga.edu.statecapitalsquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ScoreListActivity extends AppCompatActivity {

    DatabaseHelper stateCapitalsDB;
    List<ScoreCard> scoreCardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_list_activity);

        stateCapitalsDB = new DatabaseHelper(this);

        scoreCardList = new ArrayList<>();
        scoreCardList = stateCapitalsDB.allScores();

        ListView listView = findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter{



        @Override
        public int getCount() {
            return scoreCardList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.score_layout,null);

            TextView textView_date = view.findViewById(R.id.date);
            TextView textView_scores = view.findViewById(R.id.scores);
            TextView textView_questionsCorrect = view.findViewById(R.id.questionsCorrect);
            TextView textView_questionsWrong = view.findViewById(R.id.questionsWrong);

            DecimalFormat df = new DecimalFormat("0.##");


            textView_date.setText("Date: " + scoreCardList.get(i).getDate());
            textView_scores.setText("Score: " +df.format(scoreCardList.get(i).getScore())+"%");
            textView_questionsCorrect.setText("Questions Correct: " +Integer.toString(scoreCardList.get(i).getQuestionsCorrect()));
            textView_questionsWrong.setText("Questions Incorrect: " + Integer.toString(scoreCardList.get(i).getQuestionsWrong()));

            return view;
        }
    }

}
