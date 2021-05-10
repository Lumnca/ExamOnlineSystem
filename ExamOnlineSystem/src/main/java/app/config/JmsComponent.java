package app.config;

import app.datamodel.common.Message;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
@Component
public class JmsComponent {
    @Autowired
    JmsMessagingTemplate messagingTemplate;
    @Autowired
    Queue queue;
    public void send(JSONObject jsonObject){
        messagingTemplate.convertAndSend(this.queue,jsonObject);
    }
    @JmsListener( destination = "amq")
    public void receive(JSONObject object){
        System.out.println(object);
    }

}
