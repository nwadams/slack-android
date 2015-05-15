package ca.nickadams.slack.api;

import android.content.Context;

import ca.nickadams.slack.models.Auth;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class SlackApi {

    private static final String PREFS_NAME = "slack_prefs";
    private static final String KEY_TOKEN = "token";
    private static SlackApi instance;

    public static SlackApi getInstance(Context context) {
        if (instance == null) {
            instance = new SlackApi(context);
        }

        return instance;
    }

    public static SlackApi getInstance() {
        return instance;
    }

    private final RestAdapter restAdapter;
    private final Context context;

    private String authToken;
    private Auth auth;

    public SlackApi(Context context) {
        this.context = context;
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://slack.com/api")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade requestFacade) {
                        requestFacade.addQueryParam("token", getAuthToken());
                    }
                })
                .build();

        authToken = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public SlackService getSlackService() {
        return restAdapter.create(SlackService.class);
    }

    public void setAuthToken(String token) {
        authToken = token;
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().putString(KEY_TOKEN, token).apply();
    }

    public boolean hasToken() {
        return authToken != null;
    }

    private String getAuthToken() {
        return authToken;
    }

    public void setSelf(Auth auth) {
        this.auth = auth;
    }

    public Auth getSelf() {
        return auth;
    }
}
