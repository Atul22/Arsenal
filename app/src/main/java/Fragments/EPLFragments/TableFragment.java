package Fragments.EPLFragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atul.arsenal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import API.URLS;
import Adapter.EPLTableAdapter;
import JSONParser.parseLeagueTable;
import Utils.EPLTableObject;
import Utils.HTTPStuff;

public class TableFragment extends Fragment {
    ArrayList<EPLTableObject> mList;
    int position;
    HTTPStuff httpStuff;
    EPLTableAdapter mAdapter;
    RecyclerView recyclerView;
    String leagueTableUrl;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TableFragment tableFragment = new TableFragment();
        tableFragment.setArguments(bundle);
        return tableFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.epl_table_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        httpStuff = new HTTPStuff();
        String URL = URLS.getEplURL();
        recyclerView = view.findViewById(R.id.epl_card_list);
        mList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new EPLTableAdapter(mList);
        recyclerView.setAdapter(mAdapter);

        getLeagueTableUrl(URL);
    }

    public void getLeagueTableUrl(final String URL) {
        httpStuff.getData(URL, new HTTPStuff.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONObject links = object.getJSONObject("_links");
                    Log.d("JSON4", links.getJSONObject("leagueTable").getString("href"));
                    leagueTableUrl = links.getJSONObject("leagueTable").getString("href");
                    makeRequest(leagueTableUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void makeRequest(final String URL) {
        Log.v("JSON4", "url = " + URL);
        httpStuff.getData(URL, new HTTPStuff.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONArray results = object.getJSONArray("standing");
                    //Log.v("JSON4", results.getJSONObject(0).getString("crestURI"));
                    parseLeagueTable.setData(results, mList);
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}