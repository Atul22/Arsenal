package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atul.arsenal.R;

import java.util.ArrayList;

import DataObjects.FixturesObject;

public class LeagueFixtureListAdapter extends RecyclerView.Adapter<LeagueFixtureListAdapter.EPLFixtureListRowHolder>{
    private ArrayList<FixturesObject> mList;
    private Context mContext;
    public LeagueFixtureListAdapter(ArrayList<FixturesObject> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueFixtureListAdapter.EPLFixtureListRowHolder viewHolder, int i) {
        FixturesObject object = mList.get(i);

        viewHolder.homeTeamName.setText(object.gethomeTeamName());
        viewHolder.awayTeamName.setText(object.getawayTeamName());
        viewHolder.homeTeamGoals.setText(object.getHomeTeamGoals());
        viewHolder.awayTeamGoals.setText(object.getAwayTeamGoals());
        viewHolder.gameDate.setText(object.getDate());
        viewHolder.gameStatus.setText(object.getStatus());
    }

    @NonNull
    @Override
    public LeagueFixtureListAdapter.EPLFixtureListRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.league_fixtures_row, parent, false);
        return new LeagueFixtureListAdapter.EPLFixtureListRowHolder(itemView);
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
