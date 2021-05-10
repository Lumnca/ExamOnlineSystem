package app.server.exam;


import app.datamodel.exam.*;
import app.datamodel.student.StuExam;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoCheckSubject {
    @Autowired
    GetExamSubject examSubject;
    public  List<Result> checkAll(StuExam stuExam){
        int sum = 0;
        List<Result> results = new ArrayList<>();
        List<SelectSubject> selectSubjects = examSubject.getSelectSubject(stuExam);
        for (SelectSubject s1 : selectSubjects) {
            if(s1!=null){
                Result result = new Result();
                result.setScore(s1.checkAnswer());
                result.setAnswer(s1.getAnswer());
                result.setInput(s1.getSelect());
                result.setNo(++sum);
                result.setTitle(s1.getTitle());
                results.add(result);
            }
        }

        List<TkSubject> tkSubjects = examSubject.getTkSubject(stuExam);
        for (TkSubject t:tkSubjects) {
            if(t!=null){
                Result result = new Result();
                result.setScore(t.checkAnswer());
                result.setAnswer(t.textStringAnswer());
                result.setInput(t.textStringInput());
                result.setTitle(t.getTitle());
                result.setNo(++sum);
                results.add(result);
            }

        }

        List<JudgeSubject> judgeSubjects =  examSubject.getJudgeSubject(stuExam);
        for (JudgeSubject j:judgeSubjects) {
            if(j != null){
                Result result = new Result();
                result.setScore(j.checkAnswer());
                result.setAnswer(j.getAnswer());
                result.setInput(j.getSelect());
                result.setTitle(j.getTitle());
                result.setNo(++sum);
                results.add(result);
            }
        }

        List<Subjective> subjectives = examSubject.getSubjective(stuExam);
        for (Subjective s:subjectives) {
            if(s!=null){
                Result result = new Result();
                result.setScore(s.getDf()==null? 0 : s.getDf());
                result.setNo(++sum);
                result.setTitle(s.getTitle());
                result.setAnswer(s.getAnswer());
                results.add(result);
            }
        }

        return results;
    }
    public int GetSumScore(List<Result> results){
        int sum = 0;
        for (Result r:results
             ) {
            sum += r.getScore();
        }
        return sum;
    }

}
