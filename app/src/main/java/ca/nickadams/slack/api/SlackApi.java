package ca.nickadams.slack.api;

import android.content.Context;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class SlackApi {

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

    private String authToken;

    public SlackApi(Context context) {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://slack.com/api")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade requestFacade) {
                        requestFacade.addQueryParam("token", getAuthToken());
                    }
                })
                .build();
    }

    public SlackService getSlackService() {
        return restAdapter.create(SlackService.class);
    }

    private String getAuthToken() {
        return "xoxp-2171059446-2184258110-4854410584-1f90dd";
    }
}
