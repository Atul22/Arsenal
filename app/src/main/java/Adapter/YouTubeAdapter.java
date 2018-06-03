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
import Utils.AppController;
import com.example.atul.arsenal.R;

import java.util.ArrayList;

import DataObjects.YouTubeVideosObject;

import static android.content.ContentValues.TAG;

public class YouTubeAdapter extends RecyclerView.Adapter<YouTubeAdapter.YouTubeViewHolder> {
    private ArrayList<YouTubeVideosObject> videoList;
    public YouTubeAdapter(ArrayList<YouTubeVideosObject> list) {
        Log.d(TAG, "YouTubeAdapter: " + list);
        videoList = list;
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull YouTubeViewHolder youTubeViewHolder, int i) {
        YouTubeVideosObject object = videoList.get(i);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        youTubeViewHolder.title.setText(object.getTitle());
        youTubeViewHolder.thumbnail.setImageUrl(object.getHighPosterURL(), imageLoader);
    }

    @NonNull
    @Override
    public YouTubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        return new YouTubeViewHolder(itemView);
    }

    public static class YouTubeViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected NetworkImageView thumbnail;

        public YouTubeViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            thumbnail = v.findViewById(R.id.thumbnail);
        }
    }
}
