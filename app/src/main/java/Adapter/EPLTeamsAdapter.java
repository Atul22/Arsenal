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

import DataObjects.EPLTeamsObject;
import SvgUtils.SvgDecoder;
import SvgUtils.SvgDrawableTranscoder;
import SvgUtils.SvgSoftwareLayerSetter;
import Utils.AppController;

public class EPLTeamsAdapter extends RecyclerView.Adapter<EPLTeamsAdapter.EPLTeamsRowHolder> implements View.OnClickListener {

    private ArrayList<EPLTeamsObject> mList;
    private Context mContext;

    public EPLTeamsAdapter(ArrayList<EPLTeamsObject> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EPLTeamsRowHolder viewHolder, int i) {
        EPLTeamsObject object = mList.get(i);
        String serialNum = Integer.toString(i + 1);
        viewHolder.serialNum.setText(serialNum);
        viewHolder.teamName.setText(object.getName());
        if(object.getTeamLogo().endsWith(".svg")) {
            viewHolder.teamLogo.setVisibility(View.VISIBLE);
            loadImage(mContext, object.getTeamLogo(), viewHolder.teamLogo);
        } else if(object.getTeamLogo().endsWith(".png")) {
            ImageLoader imageLoader = AppController.getInstance().getImageLoader();
            viewHolder.teamLogo1.setImageUrl(object.getTeamLogo(), imageLoader);
            viewHolder.teamLogo1.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    @Override
    public EPLTeamsRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.epl_team_row, parent, false);
        return new EPLTeamsRowHolder(itemView);
    }

    @Override
    public void onClick(View v) {

    }

    public static class EPLTeamsRowHolder extends RecyclerView.ViewHolder {
        protected TextView serialNum;
        protected ImageView teamLogo;
        protected NetworkImageView teamLogo1;
        protected TextView teamName;

        private EPLTeamsRowHolder(View v) {
            super(v);
            serialNum = v.findViewById(R.id.serial_num);
            teamLogo = v.findViewById(R.id.epl_table_team_logo);
            teamLogo1 = v.findViewById(R.id.epl_table_team_logo1);
            teamName = v.findViewById(R.id.epl_table_team_name);
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
