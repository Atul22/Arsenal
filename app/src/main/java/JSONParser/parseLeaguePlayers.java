package JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.EPLPlayersObject;

public class parseLeaguePlayers {
    public static void setData(JSONArray results, ArrayList<EPLPlayersObject> list) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                EPLPlayersObject eplPlayersObject = new EPLPlayersObject();

                eplPlayersObject.setPlayerName(object.getString("name"));
                eplPlayersObject.setPlayerPosition(object.getString("position"));
                eplPlayersObject.setPlayerJersey(object.getString("jerseyNumber"));
                eplPlayersObject.setPlayerDOB(object.getString("dateOfBirth"));
                eplPlayersObject.setPlayerNationality(object.getString("nationality"));
                eplPlayersObject.setPlayerContract(object.getString("contractUntil"));

                list.add(eplPlayersObject);
            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
    }
}
