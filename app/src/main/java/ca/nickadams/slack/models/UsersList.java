package ca.nickadams.slack.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UsersList extends BaseModel implements Serializable {

    @SerializedName("members")
    public List<User> users;
}
