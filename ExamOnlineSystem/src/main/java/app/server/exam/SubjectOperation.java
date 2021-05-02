package app.server.exam;

import app.datamodel.exam.Subjective;
import app.datamodel.student.StuExam;
import org.springframework.beans.factory.annotation.Autowired;

public class SubjectOperation {
    @Autowired
    GetExamSubject getExamSubject;
    public void teacherUpdateSubject(Subjective subjective, StuExam stuExam){
    }
}
