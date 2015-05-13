package ca.nickadams.slack.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ca.nickadams.slack.R;
import ca.nickadams.slack.adapters.MessagesAdapter;
import ca.nickadams.slack.api.SlackApi;
import ca.nickadams.slack.decorations.DividerItemDecoration;
import ca.nickadams.slack.models.BaseModel;
import ca.nickadams.slack.models.Channel;
import ca.nickadams.slack.models.Message;
import ca.nickadams.slack.models.MessagesList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MessageThreadActivity extends AppCompatActivity {

    private static final String EXTRA_CHANNEL = "channel";


    public static Intent instanceForChannel(Context context, Channel channel) {
        return new Intent(context, MessageThreadActivity.class).putExtra(EXTRA_CHANNEL, channel);
    }

    @InjectView(R.id.input_message)
    EditText messageInputEditText;
    @InjectView(R.id.btn_send)
    ImageButton sendMessageButton;
    @InjectView(R.id.list_messages)
    RecyclerView messagesRecyclerView;

    private MessagesAdapter messagesAdapter;

    private Channel channel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_thread);

        ButterKnife.inject(this);

        channel = (Channel) getIntent().getSerializableExtra(EXTRA_CHANNEL);
        getSupportActionBar().setTitle("#" + channel.name);

        setupRecyclerView();

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = messageInputEditText.getText().toString();
                if (!message.isEmpty()) {
                    // todo sanitize

                    SlackApi.getInstance(MessageThreadActivity.this).getSlackService()
                            .postMessage(channel.id, message, new Callback<BaseModel>() {
                                @Override
                                public void success(BaseModel baseModel, Response response) {
                                    if (!baseModel.ok) {
                                        messageInputEditText.setText(message);
                                    }
                                }

                                @Override
                                public void failure(RetrofitError retrofitError) {
                                    messageInputEditText.setText(message);
                                }
                            });
                    messageInputEditText.setText("");
                    messagesAdapter.addMessage(message);
                    messagesRecyclerView.scrollToPosition(0);
                }
            }
        });

        SlackApi.getInstance(this).getSlackService().getChannelHistory(channel.id, new Callback<MessagesList>() {
            @Override
            public void success(MessagesList messagesList, Response response) {
                if (messagesList.ok) {
                    populateMessages(messagesList.messages);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                // womp womp
            }
        });
    }

    private void populateMessages(List<Message> messages) {
        messagesRecyclerView.setAdapter(messagesAdapter = new MessagesAdapter(messages));
    }

    private void setupRecyclerView() {
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        messagesRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }
}
