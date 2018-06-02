package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.NewsObject;

public class parseNews {
    public static void setData(JSONArray results, ArrayList<NewsObject> list) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                NewsObject newsObject = new NewsObject();
                String articleUrl = object.getString("url");
                if(!articleUrl.startsWith("https://www.bbc.co.uk/sport/football/"))
                    continue;

                newsObject.setArticleUrl(articleUrl);
                newsObject.setDescription(object.getString("description"));
                newsObject.setTitle(object.getString("title"));
                newsObject.setLogo(object.getString("urlToImage"));
                newsObject.setPublishedAt(object.getString("publishedAt"));

                list.add(newsObject);
            } catch( JSONException e ){
                Log.v("Error: ", "Error");
                e.printStackTrace();
            }
        }
    }
}
