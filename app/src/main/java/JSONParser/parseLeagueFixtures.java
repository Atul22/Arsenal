package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import DataObjects.FixturesObject;
import DataObjects.TableObject;
import DataObjects.TableObjectWC;

public class parseLeagueFixtures {
    public static void setData(JSONArray response, HashMap<String, ArrayList<FixturesObject>> map, ArrayList<String> list1) {
        Log.v("responseLen", Integer.toString(response.length()));
        for(int i = 0; i < response.length(); i++) {
            try {
                JSONObject object = response.getJSONObject(i);
                FixturesObject fixturesObject = new FixturesObject();

                fixturesObject.setHomeTeamName(object.getString("homeTeamName"));
                fixturesObject.setAwayTeamName(object.getString("awayTeamName"));
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

    public static void setChampionshipData(JSONArray response, HashMap<String, ArrayList<TableObjectWC>> map, ArrayList<String> list1) {
        Log.v("responseLen", Integer.toString(response.length()));
        ArrayList<TableObjectWC> list = new ArrayList<>();
        String group = "";
        for(int i = 0; i < response.length(); i++) {
            try {
                JSONObject object = response.getJSONObject(i);
                TableObjectWC tableObjectWC = new TableObjectWC();
                group = object.getString("group");
                tableObjectWC.setGroup(group);
                tableObjectWC.setRank(object.getString("rank"));
                tableObjectWC.setTeamName(object.getString("team"));
                tableObjectWC.setGamesPlayed(object.getString("playedGames"));
                tableObjectWC.setLogo(object.getString("crestURI"));
                tableObjectWC.setPoints(object.getString("points"));
                tableObjectWC.setGoals(object.getString("goals"));
                tableObjectWC.setGoalsAgainst(object.getString("goalsAgainst"));
                tableObjectWC.setGoalDiff(object.getString("goalDifference"));

                list.add(tableObjectWC);
            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
        map.put(group, list);
        list1.add(group);
    }
}
