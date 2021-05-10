package app.datamodel.exam;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class SelectSubject extends Subject{

    private String select;
    private String answer;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSelect() {
       return dealData(this.type,this.select);
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getAnswer() {
       return dealData(this.type,this.answer);
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String dealData(String type,String data){
        StringBuffer str = new StringBuffer("");
        if(type.equals("0")){
            List<Integer> datas = JSONObject.parseArray(data,Integer.class);
            for (Integer i : datas) {
                str.append(((char)('A'+i)));
            };
            return str.toString();
        }
        else{
            return String.valueOf((char)('A'+Integer.parseInt(data)));
        }
    }
    @Override
    public int checkAnswer() {
        if(getType().equals("1")){
            if(this.getAnswer().equals(this.getSelect())){
                super.setDf(super.getScore());
                return  super.getScore();
            }
            else{
                super.setDf(0);
                return 0;
            }
        }
        else{
            if(arrayCompare(select,answer)){
                super.setDf(super.getScore());
                return  super.getScore();
            }
            else{
                return  0;
            }
        }

    }

    public boolean arrayCompare(String str,String str2){
        List<Integer> integers1 = JSONObject.parseArray(str,Integer.class);
        List<Integer> integers2 = JSONObject.parseArray(str2,Integer.class);
        for (Integer i:integers1
        ) {
            if(integers2.indexOf(i)!=-1){
                integers2.remove(i);
            }
        }
        return integers2.size() == 0;
    }
}
