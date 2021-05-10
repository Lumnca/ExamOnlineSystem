package app.datamodel.exam;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TkSubject extends  Subject {
    private List<String> inputs;
    private String answer;

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String textStringAnswer(){
        List<Answer> answers = JSONObject.parseArray(this.answer,Answer.class);
        String str = "";
        for (Answer a: answers) {
            str = str.concat(a.getTxt().toString()+",");
        }
        return str;
    }
    public String textStringInput(){
        String str = "";
        for (String a: this.inputs) {
            str = str.concat(a + ",");
        }
        return str;
    }

    @Override
    public int checkAnswer() {
        List<Answer> answers = JSONObject.parseArray(this.answer,Answer.class);
        int score = 0;
        for (Answer a:answers) {
            int i = answers.indexOf(a);
            String input = this.inputs.get(i);
            int type = 0;
            for (String s : a.getRules()) {
                if(s.equals("A")){
                    input = input.toLowerCase();
                }
                else if(s.equals("B")){
                    type = 1;
                }
                else if(s.equals("C")){
                    type = 2;
                }
                 else if(s.equals("D")){
                    input = input.trim();
                }
                 else{
                }
            }
            if(a.compareAnswer(input,type)){
                a.setDf(a.getScore());
                score += a.getScore();
            }
        }
        return score;
    }

}
