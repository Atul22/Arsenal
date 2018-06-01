package Adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


import SvgUtils.SvgDecoder;
import SvgUtils.SvgDrawableTranscoder;
import SvgUtils.SvgSoftwareLayerSetter;
import Utils.AppController;
import DataObjects.EPLTableObject;

import static android.content.ContentValues.TAG;

public class EPLTableAdapter extends RecyclerView.Adapter<EPLTableAdapter.EPLTableRowHolder>{
    private ArrayList<EPLTableObject> tableList;
    private Context mContext;
    public EPLTableAdapter( ArrayList<EPLTableObject> list, Context context) {
        tableList = list;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EPLTableRowHolder viewHolder, int i) {
        EPLTableObject object = tableList.get(i);
        //ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        viewHolder.position.setText(object.getPosition());
        //viewHolder.teamLogo.setImageUrl(object.getLogo(), imageLoader);
        viewHolder.teamName.setText(object.getTeamName());
        viewHolder.gamesPlayed.setText(object.getGamesPlayed());
        viewHolder.gamesWon.setText(object.getGamesWon());
        viewHolder.gamesLost.setText(object.getGamesLost());
        viewHolder.goalDiff.setText(object.getGoalDiff());
        viewHolder.points.setText(object.getPoints());
        if(object.getLogo().endsWith(".svg")) {
            viewHolder.teamLogo1.setVisibility(View.VISIBLE);
            loadImage(mContext, object.getLogo(), viewHolder.teamLogo1);
        } else if(object.getLogo().endsWith(".png")) {
            ImageLoader imageLoader = AppController.getInstance().getImageLoader();
            viewHolder.teamLogo.setImageUrl(object.getLogo(), imageLoader);
            viewHolder.teamLogo.setVisibility(View.VISIBLE);
        }
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
        protected ImageView teamLogo1;
        protected TextView teamName;
        protected TextView gamesPlayed;
        protected TextView gamesWon;
        protected TextView gamesLost;
        protected TextView goalDiff;
        protected TextView points;

        private EPLTableRowHolder(View v) {
            super(v);
            position = v.findViewById(R.id.epl_position);
            teamLogo = v.findViewById(R.id.epl_team_logo);
            teamLogo1 = v.findViewById(R.id.epl_team_logo1);
            teamName = v.findViewById(R.id.epl_team_name);
            gamesPlayed = v.findViewById(R.id.epl_games_played);
            gamesWon = v.findViewById(R.id.epl_games_won);
            gamesLost = v.findViewById(R.id.epl_games_lost);
            goalDiff = v.findViewById(R.id.epl_goal_difference);
            points = v.findViewById(R.id.epl_points);
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
