package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.EPLTeamsObject;

public class parseLeagueTeams {
    public static void setData(JSONArray results, ArrayList<EPLTeamsObject> list) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                JSONObject links = object.getJSONObject("_links");
                EPLTeamsObject eplTeamsObject = new EPLTeamsObject();

                eplTeamsObject.setName(object.getString("name"));
                eplTeamsObject.setShortName(object.getString("shortName"));
                eplTeamsObject.setTeamLogo(object.getString("crestUrl"));
                eplTeamsObject.setTeamCode(object.getString("code"));
                eplTeamsObject.setTeamPlayers(links.getJSONObject("players").getString("href"));
                list.add(eplTeamsObject);
            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
    }
}
