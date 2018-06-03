package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import DataObjects.FixturesObject;

public class parseLeagueFixtures {
    public static void setData(JSONArray response, HashMap<String, ArrayList<FixturesObject>> map, ArrayList<String> list1) {
        Log.v("responseLen", Integer.toString(response.length()));
        for(int i = 0; i < response.length(); i++) {
            try {
                JSONObject object = response.getJSONObject(i);
                FixturesObject fixturesObject = new FixturesObject();

                fixturesObject.sethomeTeamName(object.getString("homeTeamName"));
                fixturesObject.setawayTeamName(object.getString("awayTeamName"));
                String matchDay = object.getString("matchday");
                fixturesObject.setMatchDay(matchDay);
                fixturesObject.setDate(object.getString("date"));
                fixturesObject.setStatus(object.getString("status"));

                JSONObject result = object.getJSONObject("result");
                fixturesObject.setHomeTeamGoals(result.getString("goalsHomeTeam"));
                fixturesObject.setAwayTeamGoals(result.getString("goalsAwayTeam"));
                if(map.containsKey(matchDay)) {
                    ArrayList<FixturesObject> list = map.get(matchDay);
                    list.add(fixturesObject);
                }else {
                    ArrayList<FixturesObject> list = new ArrayList<>();
                    list.add(fixturesObject);
                    map.put(matchDay, list);
                    list1.add(matchDay);
                }

            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
    }
}
