package edu.cnm.deepdive.retrofitapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.cnm.deepdive.retrofitapplication.models.Todo;

public class MainActivity extends AppCompatActivity {
  ActivityBroadcastReceiver receiver = null;

  class ActivityBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      if (action != null) {
        if (action.equals("POST_SENT")) {
          Button btnSend = findViewById(R.id.btnSend);
          if (btnSend != null) {
            btnSend.setEnabled(true);
          }

          Toast.makeText(MainActivity.this, "Post Successful!", Toast.LENGTH_SHORT).show();
        }
      }
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    receiver = new ActivityBroadcastReceiver();
    IntentFilter filter = new IntentFilter();
    filter.addAction("TODO_RECEIVED");
    filter.addAction("POST_SENT");
    LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

    final Button btnSend = findViewById(R.id.btnSend);
    if (btnSend != null) {
      btnSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          EditText txtTitle = findViewById(R.id.txtTitle);
          EditText txtBody = findViewById(R.id.txtBody);
          if (txtTitle != null && txtBody != null) {
            String title = txtTitle.getText().toString();
            String body = txtBody.getText().toString();
            txtTitle.setText("");
            txtBody.setText("");
            btnSend.setEnabled(false);
            RetrofitServer.createPost(
                MainActivity.this,
                title,
                body,
                1
            );
          }
        }
      });
    }

    RetrofitServer.todo(this, 1, new GetValueCallback<Todo>() {
      @Override
      protected void onResult(Todo result) {
        Log.d("MainActivity", "Retrieved todo.");
      }

      @Override
      protected void onError(String error) {

      }
    });
    RetrofitServer.posts();
  }
}
