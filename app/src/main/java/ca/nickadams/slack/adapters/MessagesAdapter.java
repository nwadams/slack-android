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
import ca.nickadams.slack.api.SlackApi;
import ca.nickadams.slack.data.SlackData;
import ca.nickadams.slack.models.Message;
import ca.nickadams.slack.models.User;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder>{

    private List<Message> messages;

    public MessagesAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Message message = messages.get(position);

        User user = SlackData.getInstance(viewHolder.itemView.getContext()).getUserForId(message.userId);

        viewHolder.userTextView.setText(user != null ? user.name : "unknown user");
        viewHolder.messageTextView.setText(message.text != null ? message.text : message.type);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(String messageText) {
        Message message = new Message();
        message.type = "message";
        message.userId = SlackApi.getInstance().getSelf().userId;
        message.text = messageText;

        messages.add(0, message);
        notifyItemInserted(0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.txt_user)
        TextView userTextView;
        @InjectView(R.id.txt_message)
        TextView messageTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }
    }
}
