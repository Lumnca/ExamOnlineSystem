package app.mapper;

import app.dao.UserInfoDao;
import app.datamodel.common.UserInfo;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserInfoMapper {
    @Insert("INSERT INTO userinfo (`id`,`tell`, `email`, `sex`, `birthday`, `address`, `info`, `imgurl`) " +
            "VALUES (#{id},#{tell}, #{email}, #{sex}, #{birthday}, #{address}, #{info}, #{imgurl})")
    int insertUserData(JSONObject object);

    @Select("SELECT us.id,us.tell,us.email,us.sex,us.birthday,us.address,us.info,u._no as no,u._username as username,us.imgurl FROM" +
            " userinfo as us,user as u where us.id = u._id and u._id = #{id}")
    UserInfo getUserData(Integer id);

    @Update("UPDATE `examonline`.`userinfo` SET `tell` = #{tell}, `email` = #{email}, `sex` = #{sex}, " +
            "`birthday` = #{birthday}, `address` = #{address}, `info` = #{info}, `imgurl` = #{imgurl}" +
            " WHERE (`id` = #{id})")
    int updateUserData(JSONObject jsonObject);


}
