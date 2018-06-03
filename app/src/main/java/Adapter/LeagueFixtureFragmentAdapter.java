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

public class LeagueFixtureFragmentAdapter extends RecyclerView.Adapter<LeagueFixtureFragmentAdapter.EPLFixtureFragmentRowHolder>{
    private ArrayList<String> parentList;
    private HashMap<String, ArrayList<FixturesObject>> map;
    private Context mContext;
    public LeagueFixtureFragmentAdapter(HashMap<String, ArrayList<FixturesObject>> list, ArrayList<String> list1, Context context) {
        mContext = context;
        parentList = list1;
        map = list;
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueFixtureFragmentAdapter.EPLFixtureFragmentRowHolder viewHolder, int i) {
        String matchDay = parentList.get(i);
        viewHolder.textView.setText(matchDay);

        viewHolder.recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(layoutManager);
        LeagueFixtureListAdapter mAdapter = new LeagueFixtureListAdapter(map.get(matchDay), mContext);
        viewHolder.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
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
