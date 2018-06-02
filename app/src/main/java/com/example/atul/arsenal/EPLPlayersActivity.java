package com.example.atul.arsenal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.EPLPlayersAdapter;
import DataObjects.EPLPlayersObject;
import JSONParser.parseLeaguePlayers;
import Utils.VolleyRequest;

public class EPLPlayersActivity extends AppCompatActivity {
    ArrayList<EPLPlayersObject> mList;
    RecyclerView recyclerView;
    EPLPlayersAdapter mAdapter;
    VolleyRequest volleyRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epl_players);
        String playersUrl = getIntent().getStringExtra("teamPlayers");
        String teamName = getIntent().getStringExtra("teamName");
        String teamLogo = getIntent().getStringExtra("teamLogo");

        recyclerView = findViewById(R.id.epl_players_list);
        mList = new ArrayList<>();
        mAdapter = new EPLPlayersAdapter(mList, getApplicationContext());
        volleyRequest = new VolleyRequest();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        makeRequest(playersUrl);
    }

    public void makeRequest(final String URL) {
        volleyRequest.getData(URL, new VolleyRequest.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONArray results = object.getJSONArray("players");
                    parseLeaguePlayers.setData(results, mList);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
