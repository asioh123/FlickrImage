package com.example.assy.flickrimage;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    Button backBtn;
    ProgressBar pb;
    List<MyTask> tasks;
    List<Images> ImagesList;
    private GridView gridView;
    private GridviewAdapter gridAdapter;
    String[] items = new String[500] ;
    RelativeLayout mealLayout ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String fName = intent.getStringExtra("value");

        addItems();

        tasks = new ArrayList<>();

        requesData("https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=500&nojsoncallback=1&format=json&tags="+fName+"&api_key=69c5d7d1ba5bc26bb87acfea53ed71fd");

    }

    public void addItems()
    {
        mealLayout=(RelativeLayout) findViewById(R.id.activityLayout);
        mealLayout.setBackgroundColor(Color.BLACK);
        gridView = (GridView) findViewById(R.id.gridView);
        backBtn = (Button)findViewById(R.id.button);
        pb= (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
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
    private void getItems()
    {
      int count=ImagesList.size();
      for(int i=0;i<count;i++)
      {
          items[i]=ImagesList.get(i).getUrl();
      }
      String image = ImagesList.get(0).getUrl();
    }




    protected void updateDispaly()
    {
        if(ImagesList!=null)
        {
            getItems();
            gridAdapter = new GridviewAdapter(Main2Activity.this,items );

            //Especificamos el adaptador
            gridView.setAdapter(gridAdapter);
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

    //function back button
    public void back(View view)
    {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);

        startActivity(i);
        setContentView(R.layout.activity_main);
    }
}
