package app.mapper;


import app.datamodel.teacher.ClassStudent;
import app.datamodel.teacher.Classes;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassesMapper {
    @Select("SELECT * from classes where uid = #{id}")
    List<Classes> getAllClasses(Integer id);

    @Insert("insert into classes values(#{id},#{uid},#{name},#{createdate},#{numbers},#{info})")
    int addClasses(Classes classes);

    @Update("update classes set numbers = (numbers+1) where id = #{id}")
    int addClassNumber(Integer id);

    @Delete("DELETE FROM classes where id = #{cid}")
    int deleteClass(Integer cid);

    @Update("UPDATE classes SET `name` = #{name}, `info` = #{info},`code` = #{code}  WHERE (`id` = #{id})")
    int update(JSONObject object);

    @Select("select s.cid,s.sid,u._username as name,s.joindate,u._no as no from student_class s,user u where cid = #{cid} and s.sid = u._id ;")
    List<ClassStudent> getClassAllStudent(Integer cid);


    @Insert("INSERT INTO student_class ( `sid`, `cid`, `joindate`) VALUES (#{sid}, #{cid}, #{date})")
    int addClassStudent(JSONObject object);

    @Delete("DELETE FROM student_class WHERE sid = #{sid} and cid = #{cid}")
    int deleteClassStudent(Integer sid,Integer cid);

    @Delete("DELETE FROM student_class WHERE cid = #{cid}")
    int deleteClassAllStudent(Integer cid);

    @Select("SELECT * FROM student_class where sid = #{sid} and cid = #{cid}")
    JSONObject isExistenceClass(Integer sid,Integer cid);

    @Select("SELECT * FROM examonline.classes where code = #{code}")
    Classes getClassByCode(String code);

    @Select("SELECT c.name as name,c.code as code,c.numbers as numbers,c.createdate as createdate,c.uid ,c.info,c.id FROM " +
            "student_class as sc ,classes as c where c.id =  sc.cid and sc.sid = #{sid}")
    List<Classes> getClassesByStudnet(Integer sid);
}
