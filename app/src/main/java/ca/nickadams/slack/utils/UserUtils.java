package ca.nickadams.slack.utils;

import android.content.Context;

import ca.nickadams.slack.api.SlackApi;
import ca.nickadams.slack.data.SlackData;
import ca.nickadams.slack.models.UsersList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserUtils {

    public static void loadUsers(final Context context) {
        SlackApi.getInstance(context).getSlackService().getUsersList(new Callback<UsersList>() {
            @Override
            public void success(UsersList usersList, Response response) {
                if (usersList.ok) {
                    SlackData.getInstance(context).setUsers(usersList.users);
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }
}
