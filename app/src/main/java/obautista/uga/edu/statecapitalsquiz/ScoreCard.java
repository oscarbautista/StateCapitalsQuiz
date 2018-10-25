package obautista.uga.edu.statecapitalsquiz;


public class ScoreCard {

    private String date;
    private double score;
    private int questionsCorrect;
    private int questionsWrong;

    public ScoreCard (String date, double score, int questionsCorrect, int questionsWrong ) {
        this.date = date;
        this.score = score;
        this.questionsCorrect = questionsCorrect;
        this.questionsWrong = questionsWrong;
    }

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
