package app.server.webapi;


import app.datamodel.common.Chat;
import app.datamodel.common.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;

@RestController
public class WebSocketRequest {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/online")
    @SendTo("/topic/greetings")
    public Message greeting(Principal principal)throws Exception{
        Message message = new Message();
        message.setName(principal.getName());
        message.setDate(new Date());
        return message;
    }
    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat){
        String from = principal.getName();
        chat.setFrom(from);
        messagingTemplate.convertAndSendToUser(chat.getTo(),"/queue/chat",chat);
    }

}
