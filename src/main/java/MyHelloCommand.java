import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyHelloCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {
        if (messageReceivedEvent.getAuthor().isBot()) {
            System.out.println("cannot reply");
        }

        Message message = messageReceivedEvent.getMessage();
        String rawMessage = message.getContentRaw();

        String prefix = "...";

        if (rawMessage.equals(prefix+"hello")) {
            MessageChannel channel = messageReceivedEvent.getChannel();
            channel.sendMessage("Hello World!").queue();

        }

    }

}
