package com.example.assy.flickrimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    private Button btnSearch;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addingItems();

        listItem();

    }

    public void addingItems()
    {
        mainListView = (ListView) findViewById( R.id.mainListView );
        btnSearch=(Button)findViewById(R.id.btnSearch);
        txtSearch=(EditText)findViewById(R.id.txtSearch);
        //dont open keyboard auto Edit text
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void listItem()
    {
        String[] Values = new String[] { "dog", "cat", "car", "flag",
                "baby", "israel", "france", "sea"};
        ArrayList<String> ValuesList = new ArrayList<String>();
        ValuesList.addAll( Arrays.asList(Values) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, ValuesList);

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = mainListView.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                i.putExtra("value", listItem.toString());
                startActivity(i);
                setContentView(R.layout.activity_main2);
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

    //button search function
    public void search(View view)
    {
        //getValue of the search text
        String resuelt=txtSearch.getText().toString();

        //move to another activity
        Intent i = new Intent(getApplicationContext(),Main2Activity.class);
        i.putExtra("value", resuelt);
        startActivity(i);
        setContentView(R.layout.activity_main2);
    }
}
