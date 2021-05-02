package app.server.exam;

import app.dao.student.StudentExamDao;
import app.datamodel.exam.*;
import app.datamodel.student.StuExam;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetExamSubject {
    private static final int SELECTSUBJECT = 1;
    private static final int TKTSUBJECT = 2;
    private static final int SUBJECTIVE = 3;
    private static final int JUDGESUBJECT = 4;
    public List<SelectSubject> getSelectSubject(StuExam stuExam){
        List<SelectSubject> selectSubjects = new ArrayList<>();
        List<Submit> submits =  JSONObject.parseArray(stuExam.getSubmit(),Submit.class);
        for (Submit s :submits
             ) {
            if(s.getType()==SELECTSUBJECT){
                selectSubjects = JSONObject.parseArray(s.getContent(),SelectSubject.class);
            }
        }
        return  selectSubjects;
    }
    public List<TkSubject> getTkSubject(StuExam stuExam){
        List<TkSubject> tkSubjects = new ArrayList<>();
        List<Submit> submits =  JSONObject.parseArray(stuExam.getSubmit(),Submit.class);
        for (Submit s :submits
        ) {
            if(s.getType()==TKTSUBJECT){
                tkSubjects = JSONObject.parseArray(s.getContent(),TkSubject.class);
            }
        }
        return tkSubjects;
    }
    public List<JudgeSubject> getJudgeSubject(StuExam stuExam){
        List<JudgeSubject>  judgeSubjects = new ArrayList<>();
        List<Submit> submits =  JSONObject.parseArray(stuExam.getSubmit(),Submit.class);
        for (Submit s :submits
        ) {
            if(s.getType()==JUDGESUBJECT){
                judgeSubjects = JSONObject.parseArray(s.getContent(),JudgeSubject.class);
            }
        }
        return judgeSubjects;
    }

    public List<Subjective> getSubjective(StuExam stuExam){
        List<Subjective> subjectives = new ArrayList<>();
        List<Submit> submits = JSONObject.parseArray(stuExam.getSubmit(),Submit.class);
        for (Submit s :submits
        ) {
            if(s.getType()==SUBJECTIVE){
                subjectives = JSONObject.parseArray(s.getContent(),Subjective.class);
            }
        }
        return  subjectives;
    }
    public List<Part> getAllExamSubject(StuExam stuExam){
        List<Part> parts = new ArrayList<>();
        Part<SelectSubject> subjectPart = new Part<>("选择题",SELECTSUBJECT,0,getSelectSubject(stuExam));
        parts.add(subjectPart);

        Part<TkSubject> tkSubjectPart = new Part<TkSubject>("填空题",TKTSUBJECT,0,getTkSubject(stuExam));
        parts.add(tkSubjectPart);

        Part<Subjective>  subjectivePart = new Part<Subjective>("主观题",SUBJECTIVE,0,getSubjective(stuExam));
        parts.add(tkSubjectPart);

        Part<JudgeSubject> judgeSubjectPart = new Part<>("判断",JUDGESUBJECT,0,getJudgeSubject(stuExam));
        parts.add(tkSubjectPart);
        return parts;
    }

}
