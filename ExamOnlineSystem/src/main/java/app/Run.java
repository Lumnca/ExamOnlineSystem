package app;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

@SpringBootApplication
public class Run {
    public static void main(String[] args){
        SpringApplication.run(Run.class);
    }
    @Bean
    Queue queue(){
        return new ActiveMQQueue("amq");
    }
}
