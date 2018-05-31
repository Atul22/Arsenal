package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atul.arsenal.R;

import java.util.ArrayList;

import DataObjects.EPLFixturesObject;
import DataObjects.TestObject;

public class EPLFixtureListAdapter extends RecyclerView.Adapter<EPLFixtureListAdapter.EPLFixtureListRowHolder>{
    private ArrayList<EPLFixturesObject> mList;
    private Context mContext;
    public EPLFixtureListAdapter(ArrayList<EPLFixturesObject> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EPLFixtureListAdapter.EPLFixtureListRowHolder viewHolder, int i) {
        EPLFixturesObject object = mList.get(i);

        viewHolder.homeTeamName.setText(object.gethomeTeamName());
        viewHolder.awayTeamName.setText(object.getawayTeamName());
        viewHolder.homeTeamGoals.setText(object.getHomeTeamGoals());
        viewHolder.awayTeamGoals.setText(object.getAwayTeamGoals());
        viewHolder.gameDate.setText(object.getDate());
        viewHolder.gameStatus.setText(object.getStatus());
    }

    @NonNull
    @Override
    public EPLFixtureListAdapter.EPLFixtureListRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.epl_fixtures_row, parent, false);
        return new EPLFixtureListAdapter.EPLFixtureListRowHolder(itemView);
    }

    public static class EPLFixtureListRowHolder extends RecyclerView.ViewHolder {
        private TextView homeTeamName;
        private TextView awayTeamName;
        private TextView homeTeamGoals;
        private TextView awayTeamGoals;
        private TextView gameDate;
        private TextView gameStatus;
        private EPLFixtureListRowHolder(View v) {
            super(v);
            homeTeamName = v.findViewById(R.id.home_team_name);
            awayTeamName = v.findViewById(R.id.away_team_name);
            homeTeamGoals = v.findViewById(R.id.home_team_goals);
            awayTeamGoals = v.findViewById(R.id.away_team_goals);
            gameDate = v.findViewById(R.id.game_date);
            gameStatus = v.findViewById(R.id.game_status);
        }
    }
}
