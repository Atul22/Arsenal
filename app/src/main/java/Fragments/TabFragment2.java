package Fragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atul.arsenal.R;

import org.json.JSONException;
import org.json.JSONObject;

import API.URLS;
import Utils.HTTPStuff;

public class TabFragment2 extends Fragment {
    int position;
    TextView textView;
    HTTPStuff httpStuff;
    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment2 tabFragment = new TabFragment2();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.tab2_text);
        httpStuff = new HTTPStuff();
        String URL = URLS.getEplURL();
        Log.d("JSON3", URL);
        makeRequest(URL);
    }

    public void makeRequest(final String URL) {
        httpStuff.getData(URL, new HTTPStuff.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    Log.d("JSON3", object.getString("caption"));
                    JSONObject links = object.getJSONObject("_links");
                    Log.d("JSON3", links.getJSONObject("teams").getString("href"));
                    Log.d("JSON3", data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
