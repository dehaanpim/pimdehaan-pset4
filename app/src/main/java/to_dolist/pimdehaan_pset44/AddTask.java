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

public class AddTask extends Activity implements OnClickListener {

    // declare variables
    private EditText subjectEditText;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layour for dialog
        setTitle("Add Task");
        setContentView(R.layout.add_task);

        subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        Button addTodoBtn = (Button) findViewById(R.id.add_task);

        // open the database
        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    // add the task to database
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_task:

                final String name = subjectEditText.getText().toString();

                dbManager.insert(name);

                Intent main = new Intent(AddTask.this, ToDoList.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}