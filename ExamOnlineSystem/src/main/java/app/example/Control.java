package app.example;

import app.dao.UserDao;
import app.dao.teacher.ClassesDao;
import app.mapper.ClassesMapper;
import app.mapper.UserMapper;
import app.server.Encryption;
import app.server.UserServer;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class Control {


    @GetMapping("/admin/index")
    public String aIndex(){
        return  "ADMIN";
    }

    @GetMapping("/teacher/index")
    public String tIndex(){
        return  "Teacher";
    }

    @GetMapping("/student/index")
    public String sIndex(){
        return "Student";
    }

    @CrossOrigin
    @GetMapping("/session/{key}")
    public String getSessionByKey(@PathVariable(name = "key") String key, HttpServletRequest request){
        if(request.getSession().getAttribute(key)!=null){
            return JSONObject.toJSONString(request.getSession().getAttribute(key));
        }
        else{
            return "NULL";
        }
    }
}
