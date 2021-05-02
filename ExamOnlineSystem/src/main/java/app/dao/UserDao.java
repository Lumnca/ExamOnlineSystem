package app.dao;

import app.datamodel.security.Role;
import app.datamodel.security.User;
import app.mapper.UserMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao implements UserMapper {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<Role> getUserRolesByUid(Integer id) {
        return userMapper.getUserRolesByUid(id);
    }

    @Override
    public JSONObject getQueryStudent(Integer id) {
        return userMapper.getQueryStudent(id);
    }

    @Override
    public List<JSONObject> getSomeStudentByKeyString(String str) {
        return userMapper.getSomeStudentByKeyString("%"+str+"%");
    }

    @Override
    public JSONObject getUserNameById(Integer id) {
        return userMapper.getUserNameById(id);
    }

    @Override
    public int insertUser(JSONObject object) {
        return userMapper.insertUser(object);
    }

    @Override
    public int addUserRole(Integer uid, Integer rid) {
        return userMapper.addUserRole(uid, rid);
    }

    @Override
    public JSONObject getUserId() {
        return userMapper.getUserId();
    }

    @Override
    public int updateUserName(String no, Integer id) {
        return  userMapper.updateUserName(no, id);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }
}