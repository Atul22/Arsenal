package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import DataObjects.EPLFixturesObject;

public class parseLeagueFixtures {
    public static void setData(JSONArray response, HashMap<String, ArrayList<EPLFixturesObject>> map, ArrayList<String> list1) {
        for(int i = 0; i < response.length(); i++) {
            try {
                JSONObject object = response.getJSONObject(i);
                EPLFixturesObject fixturesObject = new EPLFixturesObject();

                fixturesObject.sethomeTeamName(object.getString("homeTeamName"));
                fixturesObject.setawayTeamName(object.getString("awayTeamName"));
                String matchDay = object.getString("matchday");
                fixturesObject.setMatchDay(matchDay);
                fixturesObject.setDate(object.getString("date"));
                fixturesObject.setStatus(object.getString("status"));

                JSONObject result = object.getJSONObject("result");
                fixturesObject.setHomeTeamGoals(result.getString("goalsHomeTeam"));
                fixturesObject.setAwayTeamGoals(result.getString("goalsAwayTeam"));

                JSONObject resultsHT = result.getJSONObject("halfTime");
                fixturesObject.setHomeTeamGoalsHT(resultsHT.getString("goalsHomeTeam"));
                fixturesObject.setAwayTeamGoalsHT(resultsHT.getString("goalsAwayTeam"));

                if(map.containsKey(matchDay)) {
                    ArrayList<EPLFixturesObject> list = map.get(matchDay);
                    list.add(fixturesObject);
                }else {
                    ArrayList<EPLFixturesObject> list = new ArrayList<>();
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
