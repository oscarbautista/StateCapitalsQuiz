package obautista.uga.edu.statecapitalsquiz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



public class ScoresActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "ReviewJobLeadsActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    ScoreCardRecyclerAdapter adapter;
    DatabaseHelper stateCapitalDB;

    List<ScoreCard> scoreCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(DEBUG_TAG, "ReviewJobLeadsActivity.onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores_activity);

        stateCapitalDB = new DatabaseHelper(this);

        scoreCardList = new ArrayList<>();
        scoreCardList = stateCapitalDB.allScores();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        adapter = new ScoreCardRecyclerAdapter(this, scoreCardList);
        recyclerView.setAdapter(adapter);

    }
}
