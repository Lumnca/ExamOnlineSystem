package app.mapper;

import app.datamodel.student.MyExam;
import app.datamodel.teacher.Exam;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ExamMapper {
    @Insert("INSERT INTO exams (`uid`, `cid`, `name`, `type`, `startdate`, `enddate`) VALUES (#{uid}, #{cid}, #{name}, #{type}, #{startdate}, #{enddate})")
    int insertExam(JSONObject object);

    @Select("SELECT * FROM exams where uid = #{uid}")
    List<Exam> getAllExamByTeacherUid(Integer uid);

    @Update("UPDATE exams SET cid = #{cid}, name = #{name}, type = #{type}, startdate = #{startdate}, enddate = #{enddate},parts = #{parts},info = #{info},score = #{score} WHERE (id = #{id})")
    int updateExam(JSONObject object);

    @Select("SELECT * FROM exams where id = #{id}")
    Exam getExamById(Integer id);

    @Select("SELECT exams.id,student_class.sid , student_class.cid,exams.name,exams.type,exams.startdate,exams.enddate,exams.parts,exams.info" +
            " FROM examonline.student_class,exams where exams.cid = student_class.cid and student_class.sid = #{sid}")
    List<MyExam> getStudentAllExamsBySid(Integer sid);
}
