import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {

        String token = "put your token here";

        JDA jdaBuilder = JDABuilder.createDefault(token).build();

        jdaBuilder.addEventListener(new MyHelloCommand());

    }

}
