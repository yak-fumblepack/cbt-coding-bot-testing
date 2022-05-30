import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;


// TODO: Read Update "levels" and "messages sent"
public class XPSystem extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            System.out.println("cannot reply");
        }

        Message message = event.getMessage();

        User messageAuthor = message.getAuthor();
        String id = messageAuthor.getId();

        try {
            CBTUser.openUserInDBIsOpened(id);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        JSONObject dataJson = new JSONObject();
        try {
            dataJson = CBTUser.getUsersData();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject currUserIdObj = (JSONObject) dataJson.get(id);
        Long currLevel = (Long) currUserIdObj.get("level");
        Long currMessageSent = (Long) currUserIdObj.get("messages_sent");

        currMessageSent += 1;

        int randXPAmount = (int) Math.floor((Math.random()* 5) + 1);

        currLevel = randXPAmount + currLevel;
        JSONObject newUserBody = new JSONObject();
        newUserBody.put("level", currLevel);
        newUserBody.put("messages_sent", currMessageSent);

        dataJson.put(id, newUserBody);

        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter("src/main/java/data.json");
            fileWriter.write(dataJson.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
