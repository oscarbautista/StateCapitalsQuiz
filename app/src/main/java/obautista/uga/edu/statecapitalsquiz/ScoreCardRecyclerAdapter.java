package obautista.uga.edu.statecapitalsquiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * This is an adapter class for the RecyclerView to show all job leads.
 */
public class ScoreCardRecyclerAdapter extends RecyclerView.Adapter<ScoreCardRecyclerAdapter.ScoreCardHolder> {

    public static final String DEBUG_TAG = "ScoreCardRecyclerAdapter";

    private Context mCtx;
    private List<ScoreCard> scoreCardList;

    public ScoreCardRecyclerAdapter(Context ctx, List<ScoreCard> scoreCardList) {
        mCtx = ctx;
        this.scoreCardList = scoreCardList;
    }

    @NonNull
    @Override
    //returns an instance of this class
    //returns viewholder (UI elements)
    public ScoreCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.score_card, null);
        return new ScoreCardHolder(view);
    }


    @Override
    //binds data to view holder
    public void onBindViewHolder(@NonNull ScoreCardHolder holder, int position) {
        ScoreCard scoreCard = scoreCardList.get(position);

        holder.date.setText(scoreCard.getDate());
        holder.score.setText(String.valueOf(scoreCard.getScore()));
        holder.questionsCorrect.setText(String.valueOf(scoreCard.getQuestionsCorrect()));
        holder.questionsWrong.setText(String.valueOf(scoreCard.getQuestionsWrong()));
    }

    @Override
    //returns the size of the list
    public int getItemCount() {
        return scoreCardList.size();
    }

    class ScoreCardHolder extends RecyclerView.ViewHolder{

        TextView date, score, questionsCorrect, questionsWrong;
        public ScoreCardHolder(View itemView){
            super(itemView);

            date = itemView.findViewById(R.id.date);
            score = itemView.findViewById(R.id.score);
            questionsCorrect = itemView.findViewById(R.id.questionsCorrect);
            questionsWrong = itemView.findViewById(R.id.questionsWrong);

        }
    }

}
