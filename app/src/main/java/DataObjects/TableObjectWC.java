package DataObjects;

public class TableObjectWC {
    private String group;
    private String rank;
    private String teamName;
    private String gamesPlayed;
    private String logo;
    private String points;
    private String goals;
    private String goalsAgainst;
    private String goalDiff;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setGamesPlayed(String gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public String getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalDiff(String goalDiff) {
        this.goalDiff = goalDiff;
    }

    public String getGoalDiff() {
        return goalDiff;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPoints() {
        return points;
    }
}
