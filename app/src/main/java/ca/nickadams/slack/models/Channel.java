package ca.nickadams.slack.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Channel extends BaseModel implements Serializable, Comparable<Channel> {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("is_archived")
    public boolean isArchived;

    @SerializedName("is_member")
    public boolean isMember;

    @SerializedName("num_members")
    public int numMembers;

    @Override
    public int compareTo(Channel another) {
        if (another == null) {
            return -1;
        }

        if (isMember != another.isMember) {
            return isMember ? -1 : 1;
        } else {
            return name.compareTo(another.name);
        }
    }
}
