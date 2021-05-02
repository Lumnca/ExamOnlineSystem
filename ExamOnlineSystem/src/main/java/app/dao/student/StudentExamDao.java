package app.dao.student;

import app.datamodel.student.StuExam;
import app.mapper.StudentExamMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentExamDao implements StudentExamMapper {
    @Autowired
    StudentExamMapper studentExamMapper;
    @Override
    public int insertStudentExam(JSONObject object) {
        return studentExamMapper.insertStudentExam(object);
    }

    @Override
    public StuExam getStuExam(Integer sid, Integer eid) {
        return studentExamMapper.getStuExam(sid,eid);
    }

    @Override
    public int saveStudentSubmitExam(JSONObject object) {
        return studentExamMapper.saveStudentSubmitExam(object);
    }

    @Override
    public int updateExamResult(String result,Integer score, Integer id) {
        return studentExamMapper.updateExamResult(result,score, id);
    }

    @Override
    public int updatResult(JSONObject object) {
        return studentExamMapper.updatResult(object);
    }

    @Override
    public List<StuExam> getExamAllStudent(Integer eid) {
        return studentExamMapper.getExamAllStudent(eid);
    }
}
