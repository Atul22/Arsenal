package Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.atul.arsenal.R;

import java.util.ArrayList;


import Utils.AppController;
import Utils.EPLTableObject;

import static android.content.ContentValues.TAG;

public class EPLTableAdapter extends RecyclerView.Adapter<EPLTableAdapter.EPLTableRowHolder>{
    private ArrayList<EPLTableObject> tableList;
    public EPLTableAdapter( ArrayList<EPLTableObject> list) {
        Log.d(TAG, "ContactAdapter: " + list);
        tableList = list;
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EPLTableRowHolder viewHolder, int i) {
        EPLTableObject object = tableList.get(i);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        viewHolder.position.setText(object.getPosition());
        viewHolder.teamLogo.setImageUrl(object.getLogo(), imageLoader);
        viewHolder.teamName.setText(object.getTeamName());
        viewHolder.gamesPlayed.setText(object.getGamesPlayed());
        viewHolder.gamesWon.setText(object.getGamesWon());
        viewHolder.gamesLost.setText(object.getGamesLost());
        viewHolder.goalDiff.setText(object.getGoalDiff());
        viewHolder.points.setText(object.getPoints());
    }

    @NonNull
    @Override
    public EPLTableRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.epl_table_row, parent, false);
        return new EPLTableRowHolder(itemView);
    }

    public static class EPLTableRowHolder extends RecyclerView.ViewHolder {
        protected TextView position;
        protected NetworkImageView teamLogo;
        protected TextView teamName;
        protected TextView gamesPlayed;
        protected TextView gamesWon;
        protected TextView gamesLost;
        protected TextView goalDiff;
        protected TextView points;

        public EPLTableRowHolder(View v) {
            super(v);
            position = v.findViewById(R.id.epl_position);
            teamLogo = v.findViewById(R.id.epl_team_logo);
            teamName = v.findViewById(R.id.epl_team_name);
            gamesPlayed = v.findViewById(R.id.epl_games_played);
            gamesWon = v.findViewById(R.id.epl_games_won);
            gamesLost = v.findViewById(R.id.epl_games_lost);
            goalDiff = v.findViewById(R.id.epl_goal_difference);
            points = v.findViewById(R.id.epl_points);
        }
    }
}
