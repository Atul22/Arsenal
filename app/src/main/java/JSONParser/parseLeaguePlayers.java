package JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.PlayersObject;

public class parseLeaguePlayers {
    public static void setData(JSONArray results, ArrayList<PlayersObject> list) {
        for(int i = 0; i < results.length(); i++) {
            try {
                JSONObject object = results.getJSONObject(i);
                PlayersObject playersObject = new PlayersObject();

                playersObject.setPlayerName(object.getString("name"));
                playersObject.setPlayerPosition(object.getString("position"));
                playersObject.setPlayerJersey(object.getString("jerseyNumber"));
                playersObject.setPlayerDOB(object.getString("dateOfBirth"));
                playersObject.setPlayerNationality(object.getString("nationality"));
                playersObject.setPlayerContract(object.getString("contractUntil"));

                list.add(playersObject);
            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
    }
}
