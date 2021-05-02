package app.server;

import app.datamodel.security.User;
import app.dao.UserDao;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServer implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("账户不存在!");
        }
        user.set_roles(userDao.getUserRolesByUid(user.get_id()));
        return user;
    }
    @Autowired
    Encryption encryption;
    @Transactional
    public int register(JSONObject object){
        int isSuccess = 1;
        Integer id = userDao.getUserId().getInteger("id");
        String str = object.getString("_password");
        try {
            object.put("_password",encryption.encry(str));
            System.out.println(JSONObject.toJSONString(object));
            userDao.insertUser(object);
            userDao.addUserRole(id+1,object.getInteger("value"));
        }
        catch (Exception e){
            e.printStackTrace();
            isSuccess = 0;
        }
        return isSuccess;
    }


}