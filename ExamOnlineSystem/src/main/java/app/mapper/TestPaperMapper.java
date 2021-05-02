package app.mapper;


import app.datamodel.teacher.TestPaper;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TestPaperMapper {
    @Insert("INSERT INTO testpaper (`uid`, `date`, `name`, `type`, `state`) VALUES (#{uid},#{date},#{name},#{type},#{state})")
    int addTestPaper(JSONObject object);

    @Select("SELECT * FROM testpaper where uid = #{id}")
    List<TestPaper> getTestPaperByUid(Integer id);

    @Delete("DELETE FROM testpaper WHERE (id = #{id})")
    int delTestPaperById(Integer id);

    @Update("UPDATE testpaper SET name = #{name}, type = #{type} WHERE (`id` = #{id})")
    int updateTestPaperById(JSONObject object);

    @Update("UPDATE testpaper SET content =  #{content} WHERE  id = #{id}")
    int updateTestPaperContent(String content,Integer id);

    @Select("SELECT * FROM testpaper where id = #{id}")
    TestPaper getTestPaperById(Integer id);
}
