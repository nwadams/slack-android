package ca.nickadams.slack.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User extends BaseModel implements Serializable {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;
}
