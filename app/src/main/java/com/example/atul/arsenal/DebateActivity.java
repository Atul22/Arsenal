package com.example.atul.arsenal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import API.Channels;
import API.KEYS;
import API.URLS;
import Adapter.YouTubeAdapter;
import DataObjects.YouTubeVideosObject;
import JSONParser.parseAFTV;
import Utils.HTTPStuff;

public class DebateActivity extends AppCompatActivity {
    ArrayList<YouTubeVideosObject> mList;
    RecyclerView recyclerView;
    Toolbar toolbar;
    YouTubeAdapter mAdapter;
    HTTPStuff httpStuff;
    String nextPageToken;
    KEYS key;
    Channels channels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debate);

        toolbar = findViewById(R.id.debate_toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DebateActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.debate_list);
        mList = new ArrayList<>();
        mAdapter = new YouTubeAdapter(mList);
        httpStuff = new HTTPStuff();
        nextPageToken = "";
        key = new KEYS();
        channels = new Channels();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

        String URL = URLS.getPlayListItemsURL(nextPageToken, channels.getDebatePlaylistId(), key.getKEY1());

        makeRequest(URL);
    }

    public void makeRequest(final String URL) {
        httpStuff.getData(URL, new HTTPStuff.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    Log.v("URL:", URL);
                    JSONObject object = new JSONObject(data);
                    JSONArray jsonArray = object.getJSONArray("items");
                    parseAFTV.setData(jsonArray, mList, "playList");

                    mAdapter.notifyDataSetChanged();
                    if (object.getString("nextPageToken") != null) {
                        nextPageToken = object.getString("nextPageToken");
                        String url = URLS.getPlayListItemsURL(nextPageToken, channels.getDebatePlaylistId(), key.getKEY1());
                        makeRequest(url);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
