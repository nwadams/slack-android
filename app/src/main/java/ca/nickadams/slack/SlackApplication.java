package ca.nickadams.slack;

import android.app.Application;

import ca.nickadams.slack.api.SlackApi;

public class SlackApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        SlackApi.getInstance(this);
    }
}
