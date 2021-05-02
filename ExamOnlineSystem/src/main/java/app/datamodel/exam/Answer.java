package app.datamodel.exam;

import java.util.List;

class Answer{
    private List<String> txt;
    private List<String> rules;
    private Integer score;
    private Integer df;

    public List<String> getTxt() {
        return txt;
    }

    public void setTxt(List<String> txt) {
        this.txt = txt;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getDf() {
        return df;
    }

    public void setDf(Integer df) {
        this.df = df;
    }

    public boolean compareAnswer(String str,int m){
        boolean res = false;
        for (String s:this.getTxt()
             ) {
            if(m==1){
                if(str.equals(s)){
                    res = true;
                }
            }
            else{
                if(str.lastIndexOf(s)!=-1){
                    res = true;
                }
            }
        }
        return res;
    }

}