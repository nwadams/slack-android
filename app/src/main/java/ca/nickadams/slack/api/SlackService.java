package ca.nickadams.slack.api;

import ca.nickadams.slack.models.Auth;
import ca.nickadams.slack.models.BaseModel;
import ca.nickadams.slack.models.ChannelList;
import ca.nickadams.slack.models.MessagesList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface SlackService {
    @GET("/auth.test")
    void testAuth(Callback<Auth> cb);

    @GET("/channels.list?exclude_archived=1")
    void listChannels(Callback<ChannelList> cb);

    @GET("/channels.history")
    void getChannelHistory(@Query("channel") String channelId, Callback<MessagesList> cb);

    @POST("/chat.postMessage?as_user=true")
    void postMessage(@Query("channel") String channelId, @Query("text") String message, Callback<BaseModel> cb);
}
