package app.test;
import app.server.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public   static  void main(String[] args){

       Encryption encryption = new Encryption();
        System.out.println(encryption.encry("12345678"));
    }
}
