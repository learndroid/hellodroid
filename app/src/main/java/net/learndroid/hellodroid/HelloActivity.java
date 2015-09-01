package net.learndroid.hellodroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {
    // Constants
    //--------------------------------------------------------------------------
    private static final String TAG = HelloActivity.class.getName();
    private static final String HELLO_WORLD_TEXT_STATE_KEY = TAG + ".HELLO_WORLD_TEXT_STATE_KEY";
    private static final String OK_CLICK_COUNT_STATE_KEY = TAG + ".OK_CLICK_COUNT_STATE_KEY";
    private static final String OK_CLICK_COUNT_TEXT_STATE_KEY = TAG + ".OK_CLICK_COUNT_TEXT_STATE_KEY";

    // Attributes
    //--------------------------------------------------------------------------
    int okClickCount = 0;

    private EditText nameEditText;
    private Button okButton;
    private TextView helloWorldTextView;
    private TextView okClickCountTextView;

    // Life-cycle
    //--------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate()");
        this.setContentView(R.layout.activity_hello);
        this.findViews();
        if (savedInstanceState != null) {
            this.restoreInstanceState(savedInstanceState);
        }
    }

    private void findViews() {
        this.nameEditText = (EditText) this.findViewById(R.id.nameEditText);
        this.okButton = (Button) this.findViewById(R.id.okButton);
        this.helloWorldTextView = (TextView) this.findViewById(R.id.helloWorldTextView);
        this.okClickCountTextView = (TextView) this.findViewById(R.id.okClickCountTextView);
    }

    private void restoreInstanceState(@NonNull final Bundle savedInstanceState) {
        this.helloWorldTextView.setText(savedInstanceState.getString(HELLO_WORLD_TEXT_STATE_KEY));
        this.okClickCount = savedInstanceState.getInt(OK_CLICK_COUNT_STATE_KEY);
        this.okClickCountTextView.setText(savedInstanceState.getString(OK_CLICK_COUNT_TEXT_STATE_KEY));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) Log.d(TAG, "onResume()");
        this.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelloActivity.this.onOkButtonClicked();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) Log.d(TAG, "onPause()");
        this.okButton.setOnClickListener(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (BuildConfig.DEBUG) Log.d(TAG, "onSaveInstanceState()");
        outState.putString(HELLO_WORLD_TEXT_STATE_KEY, this.helloWorldTextView.getText().toString());
        outState.putInt(OK_CLICK_COUNT_STATE_KEY, this.okClickCount);
        outState.putString(OK_CLICK_COUNT_TEXT_STATE_KEY, this.okClickCountTextView.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) Log.d(TAG, "onDestroy()");
    }

    // UI events
    //--------------------------------------------------------------------------
    private void onOkButtonClicked() {
        this.okClickCount++;
        this.helloWorldTextView.setText(this.getString(
                R.string.hello_format,
                this.nameEditText.getText().toString())
        );
        this.okClickCountTextView.setText(this.getString(
                R.string.ok_click_count_format,
                this.okClickCount)
        );
        if (BuildConfig.DEBUG) Log.d(TAG, "okClickCount == " + okClickCount);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
