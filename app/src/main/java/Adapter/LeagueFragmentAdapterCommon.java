/**
 * Common adapter for league fixtures and championship table
 */
package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atul.arsenal.R;

import java.util.ArrayList;
import java.util.HashMap;

import DataObjects.FixturesObject;
import DataObjects.TableObjectWC;

public class LeagueFragmentAdapterCommon extends RecyclerView.Adapter<LeagueFragmentAdapterCommon.EPLFixtureFragmentRowHolder>{
    private ArrayList<String> parentList;
    private HashMap<String, ArrayList<FixturesObject>> map;
    private HashMap<String, ArrayList<TableObjectWC>> mapWC;
    private String leagueId = "";
    private Context mContext;

    public LeagueFragmentAdapterCommon(HashMap<String, ArrayList<FixturesObject>> list, ArrayList<String> list1, Context context) {
        mContext = context;
        parentList = list1;
        map = list;
    }

    //id is passed to avoid erasure conflict while overloading
    public LeagueFragmentAdapterCommon(HashMap<String, ArrayList<TableObjectWC>> list, ArrayList<String> list1, Context context, String id) {
        mContext = context;
        parentList = list1;
        mapWC = list;
        leagueId = id;
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueFragmentAdapterCommon.EPLFixtureFragmentRowHolder viewHolder, int i) {
        viewHolder.recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(layoutManager);

        if(!leagueId.equals("")) {
            String group = parentList.get(i);
            viewHolder.textView.setText(group);

            LeagueListAdapterCommon mAdapter = new LeagueListAdapterCommon(mapWC.get(group), mContext, leagueId);
            viewHolder.recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

        } else {
            String matchDay = parentList.get(i);
            viewHolder.textView.setText(matchDay);

            LeagueListAdapterCommon mAdapter = new LeagueListAdapterCommon(map.get(matchDay), mContext);
            viewHolder.recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public EPLFixtureFragmentRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.league_fixtures_card_layout, parent, false);
        return new EPLFixtureFragmentRowHolder(itemView);
    }


    public static class EPLFixtureFragmentRowHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RecyclerView recyclerView;
        private EPLFixtureFragmentRowHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.match_day);
            recyclerView = v.findViewById(R.id.epl_fixtures_list);
        }
    }
}
