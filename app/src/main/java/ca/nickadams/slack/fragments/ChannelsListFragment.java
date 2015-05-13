package ca.nickadams.slack.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ca.nickadams.slack.R;
import ca.nickadams.slack.adapters.ChannelsListAdapter;
import ca.nickadams.slack.api.SlackApi;
import ca.nickadams.slack.decorations.DividerItemDecoration;
import ca.nickadams.slack.models.Channel;
import ca.nickadams.slack.models.ChannelList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChannelsListFragment extends Fragment {

    public static Fragment newInstance() {
        return new ChannelsListFragment();
    }

    @InjectView(R.id.list_channels)
    RecyclerView channelsListView;

    private List<Channel> channels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channels_list, container, false);

        ButterKnife.inject(this, view);

        setupRecyclerView();

        SlackApi.getInstance(getActivity()).getSlackService().listChannels(new Callback<ChannelList>() {
            @Override
            public void success(ChannelList channelList, Response response) {
                if (channelList.ok) {
                    channels = channelList.channels;
                    Collections.sort(channels);

                    displayList();
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                // uh oh
            }
        });

        return view;
    }

    private void setupRecyclerView() {
        channelsListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        channelsListView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void displayList() {
        channelsListView.setAdapter(new ChannelsListAdapter(channels));
    }
}
