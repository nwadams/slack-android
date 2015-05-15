package ca.nickadams.slack.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ca.nickadams.slack.R;
import ca.nickadams.slack.api.SlackApi;
import ca.nickadams.slack.models.Auth;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.input_auth_token)
    EditText authTokenEditText;
    @InjectView(R.id.btn_submit_token)
    Button submitAuthTokenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        submitAuthTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = authTokenEditText.getText().toString();
                if (!TextUtils.isEmpty(token)) {
                    SlackApi.getInstance(v.getContext()).setAuthToken(token);
                    testAuthToken();
                }
            }
        });

        if (SlackApi.getInstance(this).hasToken()) {
            testAuthToken();
        }
    }

    private void testAuthToken() {
        SlackApi.getInstance(this).getSlackService().testAuth(new Callback<Auth>() {
            @Override
            public void success(Auth auth, Response response) {
                if (auth.ok) {
                    startActivity(ChannelsActivity.intentForAuth(MainActivity.this, auth));
                    Toast.makeText(MainActivity.this, auth.team + " " + auth.user, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, R.string.auth_token_invalid, Toast.LENGTH_SHORT).show();
                    SlackApi.getInstance(MainActivity.this).setAuthToken(null);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(MainActivity.this, R.string.auth_token_invalid, Toast.LENGTH_SHORT).show();
                SlackApi.getInstance(MainActivity.this).setAuthToken(null);
                authTokenEditText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
