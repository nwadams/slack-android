package ca.nickadams.slack.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ca.nickadams.slack.R;
import ca.nickadams.slack.activities.MessageThreadActivity;
import ca.nickadams.slack.models.Channel;

public class ChannelsListAdapter extends RecyclerView.Adapter<ChannelsListAdapter.ViewHolder> {

    private List<Channel> channels;

    public ChannelsListAdapter(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public ChannelsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_channel, parent, false));
    }

    @Override
    public void onBindViewHolder(ChannelsListAdapter.ViewHolder viewHolder, int position) {
        final Channel channel = channels.get(position);
        viewHolder.channelNameTextView.setText("#" + channel.name);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(MessageThreadActivity.instanceForChannel(v.getContext(), channel));
            }
        });
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.txt_channel_name)
        TextView channelNameTextView;

        public ViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }
    }
}
