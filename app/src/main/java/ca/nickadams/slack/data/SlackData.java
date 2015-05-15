package ca.nickadams.slack.data;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.nickadams.slack.models.User;

// todo This is rapidly going to become a terrible idea
public class SlackData {

    private static SlackData instance;

    private Map<String, User> usersMap;

    public static SlackData getInstance(Context context) {
        if (instance == null) {
            instance = new SlackData(context);
        }

        return instance;
    }

    public static SlackData getInstance() {
        return instance;
    }

    public SlackData(Context context) {
        usersMap = new HashMap<>();
    }

    public void setUsers(List<User> users) {
        for (User user : users) {
            usersMap.put(user.id, user);
        }
    }

    public User getUserForId(String id) {
        return usersMap.get(id);
    }
}
