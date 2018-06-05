/**
 * Common adapter for league fixtures and championship table
 */
package Adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.example.atul.arsenal.R;

import java.io.InputStream;
import java.util.ArrayList;

import DataObjects.FixturesObject;
import DataObjects.TableObjectWC;
import SvgUtils.SvgDecoder;
import SvgUtils.SvgDrawableTranscoder;
import SvgUtils.SvgSoftwareLayerSetter;
import Utils.AppController;

public class LeagueListAdapterCommon extends RecyclerView.Adapter<LeagueListAdapterCommon.EPLFixtureListRowHolder>{
    private ArrayList<FixturesObject> mList;
    private ArrayList<TableObjectWC> mListWC;
    private String leagueId = "";
    private Context mContext;

    /**
     *
     * @param list list for fixtures
     * @param context context is not required here, but passed to avoid erasure conflict
     */
    public LeagueListAdapterCommon(ArrayList<FixturesObject> list, Context context) {
        mList = list;
        mContext = context;
    }

    /**
     * @param list list for championship table
     */
    public LeagueListAdapterCommon(ArrayList<TableObjectWC> list, Context context, String id) {
        mListWC = list;
        leagueId = id;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        if (leagueId.equals("")) {
            return mList.size();
        }
        return mListWC.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueListAdapterCommon.EPLFixtureListRowHolder viewHolder, int i) {
        if (!leagueId.equals("")) {
            TableObjectWC objectWC = mListWC.get(i);
            viewHolder.teamRank.setText(objectWC.getRank());
            viewHolder.homeTeamName.setText(objectWC.getTeamName());
            viewHolder.gamesPlayed.setText(objectWC.getGamesPlayed());
            viewHolder.homeTeamGoals.setText(objectWC.getGoals());
            viewHolder.awayTeamGoals.setText(objectWC.getGoalsAgainst());
            viewHolder.goalDifference.setText(objectWC.getGoalsAgainst());
            viewHolder.points.setText(objectWC.getPoints());

            if(objectWC.getLogo().endsWith(".svg")) {
                viewHolder.teamLogo1.setVisibility(View.VISIBLE);
                loadImage(mContext, objectWC.getLogo(), viewHolder.teamLogo1);
            } else if(objectWC.getLogo().endsWith(".png")) {
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                viewHolder.teamLogo.setImageUrl(objectWC.getLogo(), imageLoader);
                viewHolder.teamLogo.setVisibility(View.VISIBLE);
            }

            viewHolder.teamRank.setVisibility(View.VISIBLE);
            viewHolder.homeTeamName.setVisibility(View.VISIBLE);
            viewHolder.gamesPlayed.setVisibility(View.VISIBLE);
            viewHolder.homeTeamGoals.setVisibility(View.VISIBLE);
            viewHolder.awayTeamGoals.setVisibility(View.VISIBLE);
            viewHolder.goalDifference.setVisibility(View.VISIBLE);
            viewHolder.points.setVisibility(View.VISIBLE);
        } else {
            FixturesObject object = mList.get(i);

            viewHolder.homeTeamName.setText(object.getHomeTeamName());
            viewHolder.awayTeamName.setText(object.getAwayTeamName());
            viewHolder.homeTeamGoals.setText(object.getHomeTeamGoals());
            viewHolder.awayTeamGoals.setText(object.getAwayTeamGoals());
            viewHolder.gameDate.setText(object.getDate());
            viewHolder.gameStatus.setText(object.getStatus());
        }
    }

    @NonNull
    @Override
    public LeagueListAdapterCommon.EPLFixtureListRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.league_fixtures_row, parent, false);
        return new LeagueListAdapterCommon.EPLFixtureListRowHolder(itemView);
    }

    public static class EPLFixtureListRowHolder extends RecyclerView.ViewHolder {
        private TextView teamRank;
        private NetworkImageView teamLogo;
        private ImageView teamLogo1;
        private TextView gamesPlayed;
        private TextView goalDifference;
        private TextView points;
        private TextView homeTeamName;
        private TextView awayTeamName;
        private TextView homeTeamGoals;
        private TextView awayTeamGoals;
        private TextView gameDate;
        private TextView gameStatus;
        private EPLFixtureListRowHolder(View v) {
            super(v);
            teamRank = v.findViewById(R.id.team_rank);
            teamLogo = v.findViewById(R.id.championship_team_logo);
            teamLogo1 = v.findViewById(R.id.championship_team_logo1);
            gamesPlayed = v.findViewById(R.id.games_played);
            goalDifference = v.findViewById(R.id.goal_difference);
            points = v.findViewById(R.id.points);
            homeTeamName = v.findViewById(R.id.home_team_name);
            awayTeamName = v.findViewById(R.id.away_team_name);
            homeTeamGoals = v.findViewById(R.id.home_team_goals);
            awayTeamGoals = v.findViewById(R.id.away_team_goals);
            gameDate = v.findViewById(R.id.game_date);
            gameStatus = v.findViewById(R.id.game_status);
        }
    }

    private static void loadImage(Context context, String url, ImageView imageView) {
        //Log.v("loadImage", url);
        GenericRequestBuilder<Uri,InputStream,SVG,PictureDrawable>
                requestBuilder = Glide.with(context)
                .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.svg_image_loading)
                .error(R.drawable.svg_image_error)
                .listener(new SvgSoftwareLayerSetter<Uri>());

        Uri uri = Uri.parse(url);
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(imageView);
    }
}
