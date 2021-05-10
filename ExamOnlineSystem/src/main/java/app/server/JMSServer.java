package app.server;

import app.config.JmsComponent;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JMSServer {
    @Autowired
    JmsComponent jmsComponent;

    @GetMapping("/jsm")
    public String jsmTest(){
        JSONObject object = new JSONObject();
        object.put("content","数据");
        jmsComponent.send(object);
        return "SUCCESS";
    }
}
