package obautista.uga.edu.statecapitalsquiz;

/**
 * Created by Oscar on 10/24/18.
 */

public class QuizQuestion {

    private String state;
    private String capital;
    private String secondCity;
    private String thirdCity;

    public QuizQuestion(String state, String capital, String secondCity, String thirdCity) {
        this.state = state;
        this.capital = capital;
        this.secondCity = secondCity;
        this.thirdCity = thirdCity;
    }

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
