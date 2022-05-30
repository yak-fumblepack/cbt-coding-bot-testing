import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

// TODO: Retrieve Level, Retrieve Messages Sent, Send it back
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
            String id = messageAuthor.getId();
            CBTUser newUser = new CBTUser(messageAuthor.getId());
            try {
                newUser.openUserInDBIsOpened(newUser.getId());
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }

            JSONObject userData = new JSONObject();
            try {
                userData = CBTUser.getUsersData();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }

            JSONObject currUserIdObj = (JSONObject) userData.get(id);
            String currLevel = String.valueOf(currUserIdObj.get("level"));
            String currMessageSent = String.valueOf(currUserIdObj.get("messages_sent"));

//            String toBeBuilt = "";
//            toBeBuilt += messageAuthor.getAsMention() + "\n";
//            toBeBuilt += "Level: " + currLevel + "\n";
//            toBeBuilt += "Messages Sent: " + currMessageSent + "\n";
//
//            MessageChannel channel = event.getChannel();
//            channel.sendMessage(toBeBuilt).queue();

            // List of things we want to add to our stats command
            // Level
            // Messages sent
            // User's profile picture
            // mention the user



            String userAvatarUrl = "";

            if (messageAuthor.getAvatarUrl() != null) {
                userAvatarUrl = messageAuthor.getAvatarUrl();
            } else {
                userAvatarUrl = messageAuthor.getDefaultAvatarUrl();
            }

            // Get user roles and find role with color and then take the color and use that in the embed

            /**
             * Might need privileged intents, JDA handles guild objects and guild members differently
             * Did not work
             */
//            Guild thisGuild = event.getGuild();
//            System.out.println(thisGuild.toString());
//            String _currUID = messageAuthor.getId();
//            System.out.println(_currUID);
//            Member memberObj = thisGuild.getMemberById(_currUID);
//            System.out.println(memberObj);
//            Color memberColor = memberObj.getColor();

            /**
             * This worked using the getMember() method which automatically
             * returns a Member object from the current message event.
             */
            Member memberObj = event.getMessage().getMember();
            System.out.println(memberObj);
            Color memberColor = memberObj.getColor();


            EmbedBuilder statsEmbed = new EmbedBuilder();

            statsEmbed.setTitle("Stats for " + messageAuthor.getName() + "#" + messageAuthor.getDiscriminator());
            statsEmbed.setDescription("This shows how many messages you sent and your current level.");
            statsEmbed.addField("Level", currLevel, true);
            statsEmbed.addField("Messages Sent", currMessageSent, true);
            statsEmbed.setColor(memberColor);
            statsEmbed.setThumbnail(userAvatarUrl);

            MessageChannel channel = event.getChannel();
            channel.sendMessageEmbeds(statsEmbed.build()).queue();
        }

    }
}
