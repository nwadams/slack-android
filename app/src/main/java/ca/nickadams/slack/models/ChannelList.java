package ca.nickadams.slack.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChannelList extends BaseModel implements Serializable {

    @SerializedName("channels")
    public List<Channel> channels;
}
