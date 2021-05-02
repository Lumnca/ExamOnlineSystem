package app.server.exam;


import app.dao.UserDao;
import app.datamodel.student.StuExam;
import app.datamodel.teacher.StudentSubjectives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadStudentSubjects {
    @Autowired
    GetExamSubject getExamSubject;
    @Autowired
    UserDao userDao;
    public List<StudentSubjectives> getSSj(List<StuExam> stuExams){
        List<StudentSubjectives> subjectivesList = new ArrayList<>();
        for (StuExam s:stuExams) {
            StudentSubjectives subjectives = new StudentSubjectives();
            subjectives.loadData(s);
            subjectives.setScore(s.getScore());
            subjectives.setName(userDao.getUserNameById(s.getSid()).getString("_username"));
            subjectives.setSubjectives(getExamSubject.getSubjective(s));

            subjectivesList.add(subjectives);
        }
        return subjectivesList;
    }

}
