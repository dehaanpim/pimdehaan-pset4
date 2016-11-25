package to_dolist.pimdehaan_pset44;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ToDoList extends ActionBarActivity {

    // declare variables
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private long _id;
    final String[] from = new String[]{DatabaseHelper._ID,
            DatabaseHelper.SUBJECT};
    final int[] to = new int[]{R.id.id, R.id.title};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.empty_list);

        // open database
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        // link list and task
        adapter = new SimpleCursorAdapter(this, R.layout.single_task, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ChangeTask.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
        /*
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dbManager.delete(id);
                return false;
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // open add task dialog when add button is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_task) {

            Intent add_mem = new Intent(this, AddTask.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
}
