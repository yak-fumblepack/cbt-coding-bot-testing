import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CBTUser {

    private String id;
    private int level;
    private int messagesSent;

    public CBTUser(String id) {
        this.id = id;
        this.level = 0;
        this.messagesSent = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(int messagesSent) {
        this.messagesSent = messagesSent;
    }

    public JSONObject getUsersData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object currFile = parser.parse(new FileReader("src/main/java/data.json"));
        JSONObject usersDataObj = (JSONObject) currFile;
        return usersDataObj;
    }


    public boolean openUserInDBIsOpened(String id) throws IOException, ParseException {
        JSONObject usersData = getUsersData();
        JSONObject userBody = new JSONObject();

        if (usersData.containsKey(id)) {
            return false;
        } else {
            usersData.put(id, userBody);
        }

        userBody.put("level", 0);
        userBody.put("messages_sent", 0);

        FileWriter fileWriter = new FileWriter("src/main/java/data.json");
        fileWriter.write(usersData.toJSONString());

        fileWriter.flush();

        return true;

    }



}
