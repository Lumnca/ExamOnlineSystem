package app.mapper;


import app.datamodel.teacher.ClassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select s.cid,s.sid,u._username,s.joindate from student_class s,user u where cid = #{cid} and s.sid = u._id ;")
    List<ClassStudent> getClassAllStudent(Integer cid);
}
