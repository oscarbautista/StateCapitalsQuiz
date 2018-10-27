package obautista.uga.edu.statecapitalsquiz;

/**
 * Created by Daniel on 10/24/18.
 */
public class ScoreCard {

    private String date;
    private double score;
    private int questionsCorrect;
    private int questionsWrong;
    /*
             This is the constructor for the score card
             @param date is the date that we took the quiz
             @param score is the score that the user made on the quiz in the past
             @param questionsCorrect is just the number of questions the person got correct on the quiz
             @param questionsWrong is just the number of questions the person got wrong on the quiz
         */
    public ScoreCard (String date, double score, int questionsCorrect, int questionsWrong ) {
        this.date = date;
        this.score = score;
        this.questionsCorrect = questionsCorrect;
        this.questionsWrong = questionsWrong;
    }

    //Below is just the getters and setters for the variables for the object
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getQuestionsCorrect() {
        return questionsCorrect;
    }

    public void setQuestionsCorrect(int questionsCorrect) {
        this.questionsCorrect = questionsCorrect;
    }

    public int getQuestionsWrong() {
        return questionsWrong;
    }

    public void setQuestionsWrong(int questionsWrong) {
        this.questionsWrong = questionsWrong;
    }
}
