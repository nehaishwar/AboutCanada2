package com.example.test.aboutcanada;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import com.example.test.aboutcanada.model.JsonData;
import com.example.test.aboutcanada.model.OpenJsonData;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


public class MainActivity extends AppCompatActivity {
    JSONParser jsonparser = new JSONParser();
    String title;
    Context context;
    JSONObject jobj = null;
    TextView tv ;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_list);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        //tv = (TextView) findViewById(R.id.text);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter();

        mRecyclerView.setAdapter(mAdapter);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(false);
                new RetrieveData().execute();
            }

        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

       //Asyntack background task to read JSON Data
        new RetrieveData().execute();
    }
    class RetrieveData extends AsyncTask<String,String,JsonData>{
        JsonData jsonData;

        private String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        public String readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("ISO-8859-1")));
                String jsonText = readAll(rd);
                return jsonText;
            } finally {
                is.close();
            }
        }

        @Override
        protected JsonData doInBackground(String... strings) {
            String jstr;
            String[] simpleJsonWeatherData = {};
            try {

                jstr = readJsonFromUrl(context.getString(R.string.url));
                jobj = new JSONObject(jstr);

                title = jobj.getString("title");

                jsonData  = OpenJsonData.getRowsStringsFromJson(MainActivity.this,jstr);
                jsonData.setTitle(title);

                Log.d("Neha Title is",jsonData.title);
            }catch(JSONException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
            catch(Exception ef) {
                ef.printStackTrace();
            }
            return jsonData;
        }

        @Override
        protected void onPostExecute(JsonData jsonData) {

            if (jsonData != null) {
                 Log.d("Neha data is",jsonData.getTitle());

                getSupportActionBar().setTitle(jsonData.getTitle());
                 mAdapter.setRowData(jsonData);
              }
        }

    }
}
