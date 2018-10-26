package obautista.uga.edu.statecapitalsquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreListActivity extends AppCompatActivity {

    DatabaseHelper stateCapitalDB;
    List<ScoreCard> scoreCardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_list_activity);

        ListView listView=(ListView) findViewById(R.id.listView);
        stateCapitalDB = new DatabaseHelper(this);

        scoreCardList = new ArrayList<>();
        scoreCardList = stateCapitalDB.allScores();

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

            TextView textView_date = (TextView)view.findViewById(R.id.date);
            TextView textView_scores = (TextView)view.findViewById(R.id.scores);
            TextView textView_questionsCorrect = (TextView)view.findViewById(R.id.questionsCorrect);
            TextView textView_questionsWrong = (TextView)view.findViewById(R.id.questionsWrong);


            textView_date.setText(scoreCardList.get(i).getDate());
            textView_scores.setText(Double.toString(scoreCardList.get(i).getScore()));
            textView_questionsCorrect.setText(Integer.toString(scoreCardList.get(i).getQuestionsCorrect()));
            textView_questionsWrong.setText(Integer.toString(scoreCardList.get(i).getQuestionsWrong()));

            return null;
        }
    }

}
