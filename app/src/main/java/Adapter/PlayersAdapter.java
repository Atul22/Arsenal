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

import DataObjects.PlayersObject;


public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.EPLPlayersRowHolder>{
    private ArrayList<PlayersObject> mList;
    private Context mContext;

    public PlayersAdapter(ArrayList<PlayersObject> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EPLPlayersRowHolder viewHolder, int i) {
        PlayersObject object = mList.get(i);
        viewHolder.playerPosition.setText(object.getPlayerPosition());
        viewHolder.playerJersey.setText(object.getPlayerJersey());
        viewHolder.playerName.setText(object.getPlayerName());
        viewHolder.playerNationality.setText(object.getPlayerNationality());
        viewHolder.playerDOB.setText(object.getPlayerDOB());
        viewHolder.playerContract.setText(object.getPlayerContract());
        
    }

    @NonNull
    @Override
    public EPLPlayersRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.league_players_row, parent, false);
        return new EPLPlayersRowHolder(itemView);
    }

    public static class EPLPlayersRowHolder extends RecyclerView.ViewHolder {
        protected TextView playerPosition;
        protected TextView playerJersey;
        protected TextView playerName;
        protected TextView playerNationality;
        protected TextView playerDOB;
        protected TextView playerContract;

        private EPLPlayersRowHolder(View v) {
            super(v);
            playerPosition = v.findViewById(R.id.player_position);
            playerJersey = v.findViewById(R.id.player_jersey);
            playerName = v.findViewById(R.id.player_name);
            playerNationality = v.findViewById(R.id.player_nationality);
            playerDOB = v.findViewById(R.id.playerDOB);
            playerContract = v.findViewById(R.id.player_contract);
        }
    }
}
