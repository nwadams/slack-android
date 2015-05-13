package ca.nickadams.slack.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ca.nickadams.slack.R;
import ca.nickadams.slack.fragments.ChannelsListFragment;
import ca.nickadams.slack.models.Auth;

public class ChannelsActivity extends AppCompatActivity {

    private static final String EXTRA_AUTH = "auth";
    private Auth auth;

    public static Intent intentForAuth(Context context, Auth auth) {
        return new Intent(context, ChannelsActivity.class).putExtra(EXTRA_AUTH, auth);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        auth = (Auth) getIntent().getSerializableExtra(EXTRA_AUTH);

        getSupportActionBar().setTitle(auth.team);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_content, ChannelsListFragment.newInstance()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_channels, menu);
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
