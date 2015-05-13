package ca.nickadams.slack.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseModel implements Serializable {

    @SerializedName("ok")
    public boolean ok;

    @SerializedName("error")
    public String error;
}
