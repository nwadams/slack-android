package ca.nickadams.slack.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message extends BaseModel implements Serializable {

    @SerializedName("type")
    public String type;

    @SerializedName("ts")
    public String timestamp;

    @SerializedName("user")
    public String userId;

    @SerializedName("text")
    public String text;
}
