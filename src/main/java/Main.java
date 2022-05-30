import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {


    public static final String PREFIX = "...";

    public static void main(String[] args) throws LoginException {

        String token = "OTYwNjQ4MjUxMzMyMjM1MzE2.GyKpCZ.VCy0i7dOsNzWMbBAt2YLSXzjWW7sf_KT1GMUP8";

        JDA jdaBuilder = JDABuilder.createDefault(token).build();

        jdaBuilder.addEventListener(new MyHelloCommand());
        jdaBuilder.addEventListener(new StatsCommand());
        jdaBuilder.addEventListener(new XPSystem());
    }

}
