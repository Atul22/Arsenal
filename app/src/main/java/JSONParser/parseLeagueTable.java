package JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.TableObject;

public class parseLeagueTable {
    public static void setData(JSONArray results, ArrayList<TableObject> list) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                TableObject tableObject = new TableObject();

                tableObject.setPosition(object.getString("position"));
                tableObject.setLogo(object.getString("crestURI"));
                tableObject.setTeamName(object.getString("teamName"));
                tableObject.setGamesPlayed(object.getString("playedGames"));
                tableObject.setGamesWon(object.getString("wins"));
                tableObject.setGamesLost(object.getString("losses"));
                tableObject.setGoalDiff(object.getString("goalDifference"));
                tableObject.setPoints(object.getString("points"));
                list.add(tableObject);

            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
    }
}
