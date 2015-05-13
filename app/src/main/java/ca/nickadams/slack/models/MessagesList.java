package ca.nickadams.slack.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MessagesList extends BaseModel implements Serializable {

    @SerializedName("latest")
    public String latest;

    @SerializedName("messages")
    public List<Message> messages;

    @SerializedName("has_more")
    public boolean hasMore;
}
