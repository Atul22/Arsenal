package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Utils.EPLTableObject;

public class parseLeagueTable {
    public static void setData(JSONArray results, ArrayList<EPLTableObject> list) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                EPLTableObject eplTableObject = new EPLTableObject();

                eplTableObject.setPosition(object.getString("position"));
                eplTableObject.setLogo(object.getString("crestURI"));
                eplTableObject.setTeamName(object.getString("teamName"));
                eplTableObject.setGamesPlayed(object.getString("playedGames"));
                eplTableObject.setGamesWon(object.getString("wins"));
                eplTableObject.setGamesLost(object.getString("losses"));
                eplTableObject.setGoalDiff(object.getString("goalDifference"));
                eplTableObject.setPoints(object.getString("points"));
                list.add(eplTableObject);

            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
    }
}
