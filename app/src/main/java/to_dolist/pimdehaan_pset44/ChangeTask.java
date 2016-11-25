package to_dolist.pimdehaan_pset44;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChangeTask extends Activity implements OnClickListener {

    // declare variables
    private EditText titleText;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout for changing task
        setTitle("Change Task");
        setContentView(R.layout.change_task);

        // open database
        dbManager = new DBManager(this);
        dbManager.open();

        titleText = (EditText) findViewById(R.id.subject_edittext);

        Button updateBtn = (Button) findViewById(R.id.btn_update);
        Button deleteBtn = (Button) findViewById(R.id.btn_delete);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");

        _id = Long.parseLong(id);

        titleText.setText(name);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    // update or delete task in database
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = titleText.getText().toString();

                dbManager.update(_id, title);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    // return to home screen
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ToDoList.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
