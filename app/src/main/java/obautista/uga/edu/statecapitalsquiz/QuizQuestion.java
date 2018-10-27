package obautista.uga.edu.statecapitalsquiz;

/**
 * Created by Oscar on 10/24/18.
 */

public class QuizQuestion {

    private String state;
    private String capital;
    private String secondCity;
    private String thirdCity;
    /*
          This is the constructor for quiz questions
          @param state is the state of the question
          @param capital is the capital of the state which is the correct answer
          @param secondCity is another city in the state
          @param thirdCity is another city in the state
      */
    public QuizQuestion(String state, String capital, String secondCity, String thirdCity) {
        this.state = state;
        this.capital = capital;
        this.secondCity = secondCity;
        this.thirdCity = thirdCity;
    }
    //Below are the getters and setters for each variable for the object
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getSecondCity() {
        return secondCity;
    }

    public void setSecondCity(String secondCity) {
        this.secondCity = secondCity;
    }

    public String getThirdCity() {
        return thirdCity;
    }

    public void setThirdCity(String thirdCity) {
        this.thirdCity = thirdCity;
    }
}
