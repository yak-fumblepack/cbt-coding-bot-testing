import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class StatsCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            System.out.println("cannot reply");
        }

        Message message = event.getMessage();
        String rawMessage = message.getContentRaw();

        String[] commandArgs = rawMessage.split(" ");

        if (commandArgs[0].equalsIgnoreCase(String.valueOf(Main.PREFIX + "stats")) || commandArgs[0].equalsIgnoreCase(String.valueOf(Main.PREFIX + "info"))) {
            User messageAuthor = event.getAuthor();
            CBTUser newUser = new CBTUser(messageAuthor.getId());
            try {
                newUser.openUserInDBIsOpened(newUser.getId());
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }





    }
}
