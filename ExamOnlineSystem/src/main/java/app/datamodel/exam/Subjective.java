package app.datamodel.exam;

public class Subjective extends  Subject {
    private String input;
    private String answer;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int checkAnswer() {
        return 0;
    }
}
