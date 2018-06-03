package Fragments.EPLFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atul.arsenal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import API.URLS;
import Adapter.LeagueFixtureFragmentAdapter;
import DataObjects.FixturesObject;
import JSONParser.parseLeagueFixtures;
import Utils.VolleyRequest;

public class FixturesFragment extends Fragment {
    int position;
    RecyclerView recyclerView;
    HashMap<String, ArrayList<FixturesObject>> map;
    private ArrayList<String> list;
    LeagueFixtureFragmentAdapter mAdapter;
    VolleyRequest volleyRequest;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        FixturesFragment fixturesFragment = new FixturesFragment();
        fixturesFragment.setArguments(bundle);
        return fixturesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        position = getArguments().getInt("pos");
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
        mAdapter = new LeagueFixtureFragmentAdapter(map, list, getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);

        String URL = URLS.getLeagueFixturesURL("445");
        makeRequest(URL);
    }

    public void makeRequest(String URL){
        volleyRequest.getData(URL, new VolleyRequest.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    parseLeagueFixtures.setData(object.getJSONArray("fixtures"), map, list);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
