package Fragments.LeagueFragments;

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
import java.util.HashMap;

import API.URLS;
import Adapter.LeagueFragmentAdapterCommon;
import DataObjects.TableObjectWC;
import JSONParser.parseLeagueFixtures;
import Utils.VolleyRequest;

public class TableFragmentChampionship extends Fragment {
    int position;
    private String leagueId;
    RecyclerView recyclerView;
    HashMap<String, ArrayList<TableObjectWC>> map;
    private ArrayList<String> list;
    LeagueFragmentAdapterCommon mAdapter;
    VolleyRequest volleyRequest;
    private String[] groups = {"A", "B", "C", "D", "E", "F", "G", "H"};

    public static Fragment getInstance(int position, String leagueId) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        bundle.putString("leagueId", leagueId);
        TableFragmentChampionship fragment = new TableFragmentChampionship();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        position = getArguments().getInt("pos");
        leagueId = getArguments().getString("leagueId");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.league_fixtures_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.epl_fixtures_fragment_list);
        recyclerView.setHasFixedSize(true);

        map = new HashMap<>();
        list = new ArrayList<>();
        volleyRequest = new VolleyRequest();
        mAdapter = new LeagueFragmentAdapterCommon(map, list, getContext(), leagueId);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);

        String URL = URLS.getLeagueCompetetionUrl(leagueId);
        makeRequest(URL);
    }

    public void makeRequest(String URL){
        Log.v("worldcup", URL);
        volleyRequest.getData(URL, new VolleyRequest.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONObject standings = object.getJSONObject("standings");
                    Log.v("worldcup", standings.toString());
                    for(int i = 0; i < standings.length(); i++) {
                        JSONArray groupArray = standings.getJSONArray(groups[i]);
                        Log.v("worldcup:", groupArray.toString());
                        parseLeagueFixtures.setChampionshipData(groupArray, map, list);
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
