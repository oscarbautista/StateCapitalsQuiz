package obautista.uga.edu.statecapitalsquiz;


public class ScoreCard {

    private int   id;
    private String date;
    private int score;
    private int questionsCorrect;
    private int questionsWrong;

    public ScoreCard()
    {
        this.id = -1;
        this.date = null;
        this.score = -1;
        this.questionsCorrect = -1;
        this.questionsWrong = -1;
    }

    public ScoreCard (String date, int score, int questionsCorrect, int questionsWrong ) {
        this.id = -1;
        this.date = date;
        this.score = score;
        this.questionsCorrect = questionsCorrect;
        this.questionsWrong = questionsWrong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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

    public String toString() {
        return id + ": " + date + " " + score + " " + questionsCorrect + " " + questionsWrong;
    }
}
