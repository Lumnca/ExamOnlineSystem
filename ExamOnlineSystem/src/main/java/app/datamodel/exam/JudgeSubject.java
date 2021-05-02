package app.datamodel.exam;

public class JudgeSubject extends Subject {
    private String answer;
    private String select;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    @Override
    public int checkAnswer() {
        return this.getAnswer().equals(this.getSelect())? this.getScore():0;
    }
}
