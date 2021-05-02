package app.dao;

import app.datamodel.common.UserInfo;
import app.mapper.UserInfoMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoDao implements UserInfoMapper {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public int insertUserData(JSONObject object) {
        return userInfoMapper.insertUserData(object);
    }

    @Override
    public UserInfo getUserData(Integer id) {
        return userInfoMapper.getUserData(id);
    }

    @Override
    public int updateUserData(JSONObject jsonObject) {
        return userInfoMapper.updateUserData(jsonObject);
    }
}
