package app.mapper;


import app.datamodel.student.StuExam;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentExamMapper {
    @Insert("INSERT INTO stuexam (`sid`, `eid`, `state`, `submit`, `ip`, `date`, `message`, `info`) " +
            "VALUES (#{sid},#{eid},#{state},#{submit},#{ip},#{date},#{message},#{info})")
    int insertStudentExam(JSONObject object);


    @Select("SELECT * FROM examonline.stuexam where sid = #{sid} and eid = #{eid}")
    StuExam getStuExam(Integer sid,Integer eid);


    @Update("UPDATE stuexam SET `state` = #{state}, `submit` = #{submit}, `ip` = #{ip}, `date` = #{date}, " +
            "`message` = #{message}, `info` =  #{info} WHERE ( sid =  #{sid} and eid = #{eid})")
    int saveStudentSubmitExam(JSONObject object);

    @Update("UPDATE stuexam SET `result` = #{result},`score` = #{score} WHERE (`id` = #{id})")
    int updateExamResult(String result,Integer score,Integer id);

    @Update("UPDATE stuexam SET `submit` = #{submit},`score` = #{score} WHERE sid =  #{sid} and eid = #{eid}")
    int updatResult (JSONObject object);


    @Select("SELECT * FROM stuexam where eid = #{eid}")
    List<StuExam> getExamAllStudent(Integer eid);
}
