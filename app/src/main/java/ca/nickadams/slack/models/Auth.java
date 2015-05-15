package ca.nickadams.slack.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Auth extends BaseModel implements Serializable {

    @SerializedName("team")
    public String team;

    @SerializedName("user")
    public String user;

    @SerializedName("user_id")
    public String userId;
}
