package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.AFTVObject;

public class parseAFTV {
    public static void setData(JSONArray results, ArrayList<AFTVObject> list) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                AFTVObject aftvObject = new AFTVObject();

                JSONObject id = object.getJSONObject("id");
                aftvObject.setVideoId(id.getString("videoId"));

                JSONObject snippet = object.getJSONObject("snippet");
                aftvObject.setTitle(snippet.getString("title"));
                aftvObject.setPublishedAt(snippet.getString("publishedAt"));

                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                aftvObject.setPosterURL( thumbnails.getJSONObject( "default" ).getString("url"));
                aftvObject.setHighPosterURL( thumbnails.getJSONObject("high").getString("url"));
                list.add(aftvObject);
            } catch( JSONException e ){
                Log.v("Error: ", "Error");
                e.printStackTrace();
            }
        }
    }
}
