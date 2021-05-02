package app.dao.teacher;

import app.datamodel.student.MyExam;
import app.datamodel.teacher.Exam;
import app.mapper.ExamMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExamDao implements ExamMapper {
    @Autowired
    ExamMapper examMapper;
    @Override
    public int insertExam(JSONObject object) {
        return examMapper.insertExam(object);
    }
    @Override
    public List<Exam> getAllExamByTeacherUid(Integer uid) {
        return examMapper.getAllExamByTeacherUid(uid);
    }
    @Override
    public int updateExam(JSONObject object) {
        return examMapper.updateExam(object);
    }

    @Override
    public Exam getExamById(Integer id) {
        return examMapper.getExamById(id);
    }

    @Override
    public List<MyExam> getStudentAllExamsBySid(Integer sid) {
        return examMapper.getStudentAllExamsBySid(sid);
    }
}
