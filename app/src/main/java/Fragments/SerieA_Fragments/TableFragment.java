package Fragments.SerieA_Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import Adapter.LeagueTableAdapter;
import DataObjects.TableObject;
import JSONParser.parseLeagueTable;
import Utils.VolleyRequest;

public class TableFragment extends Fragment {
    ArrayList<TableObject> mList;
    int position;
    VolleyRequest volleyRequest;
    LeagueTableAdapter mAdapter;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.league_table_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        volleyRequest = new VolleyRequest();
        recyclerView = view.findViewById(R.id.epl_card_list);
        mList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new LeagueTableAdapter(mList, getContext());
        recyclerView.setAdapter(mAdapter);

        String URL = URLS.getLeagueURL("456");
        getLeagueTableUrl(URL);
    }

    public void getLeagueTableUrl(final String URL) {
        volleyRequest.getData(URL, new VolleyRequest.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONObject links = object.getJSONObject("_links");
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
        volleyRequest.getData(URL, new VolleyRequest.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONArray results = object.getJSONArray("standing");
                    parseLeagueTable.setData(results, mList);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
