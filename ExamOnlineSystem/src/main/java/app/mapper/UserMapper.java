package app.mapper;


import app.datamodel.security.Role;
import app.datamodel.security.User;
import app.datamodel.teacher.Classes;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where _username = #{username}")
    User loadUserByUsername(String username);
    @Select(" SELECT * FROM role r, user_role ur where r.id = ur.rid and ur.uid = #{id}")
    List<Role> getUserRolesByUid(Integer id);

    @Select("select * from user where _id = #{id}")
    JSONObject getQueryStudent(Integer id);

    @Select("SELECT * FROM user where _no like #{str}")
    List<JSONObject> getSomeStudentByKeyString(String str);

    @Select("SELECT _username FROM examonline.user where _id = #{id}")
    JSONObject getUserNameById(Integer id);

    @Insert("INSERT INTO user (`_password`, `_username`, `_locked`, `_enabled`, `_no`)" +
            " VALUES (#{_password}, #{_username}, #{_locked}, #{_enabled}, #{_no})")
    int insertUser(JSONObject object);

    @Insert("INSERT INTO user_role (`uid`, `rid`) VALUES (#{uid},#{rid})")
    int addUserRole(Integer uid,Integer rid);

    @Select("SELECT count(*) as id FROM examonline.user")
    JSONObject getUserId();

    @Update("UPDATE `examonline`.`user` SET `_no` = #{no} WHERE (`_id` = #{id})")
    int updateUserName(String no,Integer id);
}
