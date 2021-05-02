package app.server;

import app.datamodel.common.Response;
import org.springframework.stereotype.Service;

@Service
public class ResponseServer {
    public  Response operationJudge(int judge,String success,String fail){
        if(judge != 0){
            return new Response(200,success);
        }
        else{
            return new Response(400,fail);
        }
    }
}
