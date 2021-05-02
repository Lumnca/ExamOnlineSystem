package app.datamodel.exam;

public class SelectSubject extends Subject{

    private String select;
    private String answer;

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int checkAnswer() {
        if(this.getAnswer().equals(this.getSelect())){
            super.setDf(super.getScore());
            return  super.getScore();
        }
        else{
            super.setDf(0);
            return 0;
        }
    }
}
