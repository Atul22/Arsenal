package Fragments.EPLFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atul.arsenal.EPLTeamsActivity;
import com.example.atul.arsenal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import API.URLS;
import Adapter.EPLTeamsAdapter;
import DataObjects.EPLTeamsObject;
import JSONParser.parseLeagueTeams;
import Utils.RecyclerItemClickListener;
import Utils.VolleyRequest;

public class TeamsFragment extends Fragment {
    ArrayList<EPLTeamsObject> mList;
    int position;
    RecyclerView recyclerView;
    EPLTeamsAdapter mAdapter;
    VolleyRequest volleyRequest;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TeamsFragment teamsFragment = new TeamsFragment();
        teamsFragment.setArguments(bundle);
        return teamsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.epl_teams_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.epl_teams_list);
        mList = new ArrayList<>();
        String URL = URLS.getEplTeamsURL();
        volleyRequest = new VolleyRequest();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new EPLTeamsAdapter(mList, getContext());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                EPLTeamsObject object = mList.get(position);
                Intent intent = new Intent(getActivity(), EPLTeamsActivity.class);
                intent.putExtra("teamPlayers", object.getTeamPlayers());
                startActivity(intent);
            }
        }));

        makeRequest(URL);

    }

    public void makeRequest(final String URL) {
        volleyRequest.getData(URL, new VolleyRequest.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONArray results = object.getJSONArray("teams");
                    parseLeagueTeams.setData(results, mList);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
