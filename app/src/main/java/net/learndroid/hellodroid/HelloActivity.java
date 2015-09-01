package net.learndroid.hellodroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {

    // Attributes
    //--------------------------------------------------------------------------
    private EditText nameEditText;
    private Button okButton;
    private TextView helloWorldTextView;

    // Life-cycle
    //--------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_hello);
        this.findViews();
    }

    private void findViews() {
        this.nameEditText = (EditText) this.findViewById(R.id.nameEditText);
        this.okButton = (Button) this.findViewById(R.id.okButton);
        this.helloWorldTextView = (TextView) this.findViewById(R.id.helloWorldTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelloActivity.this.onOkButtonClicked();
            }
        });
    }

    private void onOkButtonClicked() {
        this.helloWorldTextView.setText(this.getString(
                        R.string.hello_format,
                        this.nameEditText.getText().toString())
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.okButton.setOnClickListener(null);
    }

    // UI events
    //--------------------------------------------------------------------------
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
