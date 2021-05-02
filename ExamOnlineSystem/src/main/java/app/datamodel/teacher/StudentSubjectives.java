package app.datamodel.teacher;

import app.datamodel.exam.Result;
import app.datamodel.exam.Subjective;
import app.datamodel.student.StuExam;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class StudentSubjectives {
    private Integer sid;
    private Integer eid;
    private List<Subjective> subjectives;
    private String name;
    private String date;
    private Integer score;
    private List<Result> results;
    private String submit;

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public List<Subjective> getSubjectives() {
        return subjectives;
    }

    public void setSubjectives(List<Subjective> subjectives) {
        this.subjectives = subjectives;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void loadData(StuExam s){
        this.setDate(s.getDate());
        this.setEid(s.getEid());
        this.setSid(s.getSid());
        this.setResults(JSONObject.parseArray(s.getResult(),Result.class));
        this.setSubmit(s.getSubmit());
    }
}
