const SockJSName = "/chat";
const SocketPointName = "/user/queue/chat";
const SocketBoredName = "/app/chat";
const SocketGreetNmae = "/topic/greetings";

var stompClient = null;

function connect(callback) {
    var  socket = new SockJS(SockJSName);
    stompClient = Stomp.over(socket);
    stompClient.connect({},function (frame) {
        stompClient.subscribe(SocketPointName,function (chat) {
            callback(JSON.parse(chat.body));
        });
        stompClient.subscribe(SocketGreetNmae,function (greeting) {
            callback(JSON.stringify(greeting));
        });
        stompClient.subscribe(SocketBoredName,function (greeting) {
            callback(JSON.stringify(greeting));
        });
    });

}

function sendMeg(name,data) {
    stompClient.send(name,{},data);
}