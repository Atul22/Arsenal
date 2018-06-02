package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.atul.arsenal.R;

import java.util.ArrayList;

import DataObjects.NewsObject;
import Utils.AppController;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<NewsObject> mList;
    Context mContext;
    public NewsAdapter(ArrayList<NewsObject> list, Context context){
        mList = list;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder viewHolder, int i) {
        NewsObject object = mList.get(i);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        viewHolder.title.setText(object.getTitle());
        viewHolder.thumbnail.setImageUrl(object.getLogo(), imageLoader);
        viewHolder.publishedAt.setText(object.getPublishedAt());
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        return new NewsViewHolder(itemView);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected NetworkImageView thumbnail;
        protected TextView publishedAt;

        public NewsViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            thumbnail = v.findViewById(R.id.thumbnail);
            publishedAt = v.findViewById(R.id.publishedAt);
        }
    }
}
