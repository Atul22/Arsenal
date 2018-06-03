package JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.TeamsObject;

public class parseLeagueTeams {
    public static void setData(JSONArray results, ArrayList<TeamsObject> list) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                JSONObject links = object.getJSONObject("_links");
                TeamsObject teamsObject = new TeamsObject();

                teamsObject.setName(object.getString("name"));
                teamsObject.setShortName(object.getString("shortName"));
                teamsObject.setTeamLogo(object.getString("crestUrl"));
                teamsObject.setTeamCode(object.getString("code"));
                teamsObject.setTeamPlayers(links.getJSONObject("players").getString("href"));
                list.add(teamsObject);
            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
    }
}
