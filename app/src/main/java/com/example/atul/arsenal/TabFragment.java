package com.example.atul.arsenal;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.ContactAdapter;
import DataObjects.AFTVObject;
import JSONParser.parseAFTV;
import Utils.ContactInfo;
import Utils.HTTPStuff;
import YouTubeAPI.Channels;
import YouTubeAPI.KEYS;
import YouTubeAPI.URLS;

public class TabFragment extends Fragment {
    private static ArrayList<AFTVObject> list;
    int position;
    List<ContactInfo> list1;
    ContactAdapter mAdapter;
    RecyclerView recyclerView;
    KEYS key;
    Channels channel;
    HTTPStuff httpStuff;
    String nextPageToken;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list1 = new ArrayList<>();
        list = new ArrayList<>();
        key = new KEYS();
        channel = new Channels();
        httpStuff = new HTTPStuff();
        nextPageToken = new String();

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ContactAdapter(list1);
        recyclerView.setAdapter(mAdapter);
        createData();

        String URL = URLS.getPageURL(key.getKEY(), channel.getChannelId(), "");
        Log.v( "JSON: ", URL);
        makeRequest(URL);

    }

    public void makeRequest(final String URL) {
        httpStuff.getData(URL, new HTTPStuff.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    Log.v( "JSON: ", data);
                    nextPageToken = object.getString("nextPageToken");
                    JSONArray results = object.getJSONArray("items");
                    parseAFTV.setData(results, list);

                    //customAdapter.notifyDataSetChanged();
                    if (object.getString("nextPageToken") != null) {
                        String url = URLS.getPageURL(key.getKEY(), channel.getChannelId(), nextPageToken);
                        makeRequest(url);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createData() {
        ContactInfo info = new ContactInfo("Atul", "Kumar", "atul.kumar@iitrpr.ac.in");
        list1.add(info);

        info = new ContactInfo("Zeeshan", "Kareem", "zeeshan.kareem@iitrpr.ac.in");
        list1.add(info);

        info = new ContactInfo("Zeeshan", "Kareem", "zeeshan.kareem@iitrpr.ac.in");
        list1.add(info);

        info = new ContactInfo("Zeeshan", "Kareem", "zeeshan.kareem@iitrpr.ac.in");
        list1.add(info);

        info = new ContactInfo("Zeeshan", "Kareem", "zeeshan.kareem@iitrpr.ac.in");
        list1.add(info);

        info = new ContactInfo("Zeeshan", "Kareem", "zeeshan.kareem@iitrpr.ac.in");
        list1.add(info);

        info = new ContactInfo("Zeeshan", "Kareem", "zeeshan.kareem@iitrpr.ac.in");
        list1.add(info);

        info = new ContactInfo("Zeeshan", "Kareem", "zeeshan.kareem@iitrpr.ac.in");
        list1.add(info);
    }
}
