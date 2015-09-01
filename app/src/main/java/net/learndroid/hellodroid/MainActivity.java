package net.learndroid.hellodroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Constants
    //--------------------------------------------------------------------------
    private static final String TAG = MainActivity.class.getName();
    private static final int SAY_HELLO_REQUEST_CODE = 42;

    // Attributes
    //--------------------------------------------------------------------------
    private Button sayHelloButton;

    // Life-cycle
    //--------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate()");
        this.setContentView(R.layout.activity_main);
        this.findViews();
    }

    private void findViews() {
        if (BuildConfig.DEBUG) Log.v(TAG, "findViews()");
        this.sayHelloButton = (Button) this.findViewById(R.id.sayHelloButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreateOptionsMenu()");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) Log.d(TAG, "onResume()");
        this.sayHelloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onSayHelloButtonClicked();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (BuildConfig.DEBUG) Log.d(TAG, "onActivityResult()");
        if (requestCode == SAY_HELLO_REQUEST_CODE) {
            this.handleSayHelloRequest(resultCode, data);
        }
    }

    private void handleSayHelloRequest(int resultCode, Intent data) {
        if (BuildConfig.DEBUG) Log.d(TAG, "handleSayHelloRequest()");
        final int okClickCount = HelloActivity.okClickCountForResult(resultCode, data);
        if (okClickCount == HelloActivity.NO_OK_CLICK_COUNT) {
            return;
        }
        Toast.makeText(
                this,
                this.getString(R.string.ok_click_count_format, okClickCount),
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) Log.d(TAG, "onPause()");
        this.sayHelloButton.setOnClickListener(null);
    }

    // UI events
    //--------------------------------------------------------------------------
    private void onSayHelloButtonClicked() {
        if (BuildConfig.DEBUG) Log.d(TAG, "onSayHelloButtonClicked()");
        this.startActivityForResult(
                HelloActivity.newStartIntent(this, this.getString(R.string.say_hello)),
                SAY_HELLO_REQUEST_CODE
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onOptionsItemSelected()");
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
