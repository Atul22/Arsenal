package Fragments.MainFragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atul.arsenal.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import API.KEYS;
import API.URLS;
import Adapter.NewsAdapter;
import DataObjects.NewsObject;
import JSONParser.parseNews;
import Utils.HTTPStuff;
import Utils.RecyclerItemClickListener;

public class MainFragment1 extends Fragment {
    ArrayList<NewsObject> mList;
    int position;
    RecyclerView recyclerView;
    KEYS key;
    HTTPStuff httpStuff;
    NewsAdapter mAdapter;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        MainFragment1 mainFragment1 = new MainFragment1();
        mainFragment1.setArguments(bundle);
        return mainFragment1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =  view.findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        key = new KEYS();
        httpStuff = new HTTPStuff();
        mList = new ArrayList<>();
        mAdapter = new NewsAdapter(mList, getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        DateTime dateTime = new DateTime().minusDays(7);
        String date = dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd"));

        String URL = URLS.getNewsfeedUrl(key.getNewsAPIKEY(), date);
        getTotalResults(URL);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsObject object = mList.get(position);
                String url = object.getArticleUrl();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(getContext(), Uri.parse(url));
            }
        }));

    }

    public void getTotalResults(final String URL) {
        httpStuff.getData(URL, new HTTPStuff.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    String totalResults = object.getString("totalResults");
                    Log.v("totalResults", totalResults);
                    makeRequest(URL, Integer.parseInt(totalResults));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void makeRequest(String URL, int totalResults) {
        int perPage = 20;
        for(int i = 1; i <= 1; i++) {
            String url = URL + "&page=" + i;
            Log.v("URL:", url);
            httpStuff.getData(url, new HTTPStuff.VolleyCallBack() {
                @Override
                public void onSuccess(String data) {
                    try {
                        JSONObject object = new JSONObject(data);
                        JSONArray jsonArray = object.getJSONArray("articles");
                        parseNews.setData(jsonArray, mList);
                        mAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
