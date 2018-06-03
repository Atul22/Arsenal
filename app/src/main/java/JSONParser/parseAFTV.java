package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.YouTubeVideosObject;

public class parseAFTV {
    public static void setData(JSONArray results, ArrayList<YouTubeVideosObject> list, String type) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                YouTubeVideosObject youTubeVideosObject = new YouTubeVideosObject();
                JSONObject id = new JSONObject();

                JSONObject snippet = object.getJSONObject("snippet");
                if(type.equals("playList")) {
                    id = snippet.getJSONObject("resourceId");
                } else {
                    id = object.getJSONObject("id");
                }
                youTubeVideosObject.setVideoId(id.getString("videoId"));
                youTubeVideosObject.setTitle(snippet.getString("title"));
                youTubeVideosObject.setPublishedAt(snippet.getString("publishedAt"));

                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                youTubeVideosObject.setPosterURL( thumbnails.getJSONObject( "default" ).getString("url"));
                youTubeVideosObject.setHighPosterURL( thumbnails.getJSONObject("high").getString("url"));
                list.add(youTubeVideosObject);
            } catch( JSONException e ){
                Log.v("Error: ", "Error");
                e.printStackTrace();
            }
        }
    }
}
