package com.example.assy.flickrimage;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    TextView val;
    Button backBtn;
    TextView outPot;
    ProgressBar pb;
    List<MyTask> tasks;
    List<Images> ImagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String fName = intent.getStringExtra("value");

        val = (TextView)findViewById(R.id.textView);
        backBtn = (Button)findViewById(R.id.button);
        val.setMovementMethod(new ScrollingMovementMethod());

        pb= (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

        requesData("https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=3&nojsoncallback=1&format=json&tags=animal&api_key=69c5d7d1ba5bc26bb87acfea53ed71fd");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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

    public void backControl(View view)
    {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        setContentView(R.layout.activity_main);
    }


    protected void requesData(String uri)
    {
        MyTask task = new MyTask();
        task.execute(uri);

    }

    protected void updateDispaly()
    {
        if(ImagesList!=null)
        {
            for(Images image : ImagesList)
            {
                val.append(image.getId() + "  " + image.getOwner() + "  "  + "\n");
            }
        }
    }


    private class MyTask extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {

            if(tasks.size()==0)
            {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        protected String doInBackground(String ... params)
        {
            String content = Connect.getData(params[0]);
            return content;
        }

        protected void onPostExecute(String result)
        {

            ImagesList = jsonParse.parseFeed(result);
            updateDispaly();

            tasks.remove(this);
            if(tasks.size()==0)
            {
                pb.setVisibility(View.INVISIBLE);
            }
        }

    }
}
