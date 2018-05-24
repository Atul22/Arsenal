package Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.atul.arsenal.AppController;
import com.example.atul.arsenal.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Utils.AFTVObject;
import Utils.ContactInfo;

import static android.content.ContentValues.TAG;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.AFTVViewHolder> {
    private ArrayList<AFTVObject> videoList;
    public ContactAdapter( ArrayList<AFTVObject> list) {
        Log.d(TAG, "ContactAdapter: " + list);
        videoList = list;
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @Override
    public void onBindViewHolder(AFTVViewHolder aftvViewHolder, int i) {
        AFTVObject object = videoList.get(i);
        Log.v( "JSON1: ", object.getTitle());
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        aftvViewHolder.title.setText(object.getTitle());
        aftvViewHolder.thumbnail.setImageUrl(object.getHighPosterURL(), imageLoader);
    }

    @Override
    public AFTVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        return new AFTVViewHolder(itemView);
    }

    public static class AFTVViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected NetworkImageView thumbnail;

        public AFTVViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            thumbnail = (NetworkImageView) v.findViewById(R.id.thumbnail);
        }
    }
}
