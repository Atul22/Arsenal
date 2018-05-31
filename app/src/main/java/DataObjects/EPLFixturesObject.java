package DataObjects;

public class EPLFixturesObject {
    private String homeTeamName;
    private String awayTeamName;
    private String homeTeamLogo;
    private String awayTeamLogo;
    private String homeTeamGoals;
    private String homeTeamGoalsHT;
    private String awayTeamGoals;
    private String awayTeamGoalsHT;
    private String date;
    private String matchDay;
    private String status;

    public String gethomeTeamName() {
        return homeTeamName;
    }

    public void sethomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getawayTeamName() {
        return awayTeamName;
    }

    public void setawayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getHomeTeamLogo() {
        return homeTeamLogo;
    }

    public void setHomeTeamLogo(String homeTeamLogo) {
        this.homeTeamLogo = homeTeamLogo;
    }

    public String getAwayTeamLogo() {
        return awayTeamLogo;
    }

    public void setAwayTeamLogo(String awayTeamLogo) {
        this.awayTeamLogo = awayTeamLogo;
    }

    public String getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(String homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public String getHomeTeamGoalsHT() {
        return homeTeamGoalsHT;
    }

    public void setHomeTeamGoalsHT(String homeTeamGoalsHT) {
        this.homeTeamGoalsHT = homeTeamGoalsHT;
    }

    public String getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(String awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public String getAwayTeamGoalsHT() {
        return awayTeamGoalsHT;
    }

    public void setAwayTeamGoalsHT(String awayTeamGoalsHT) {
        this.awayTeamGoalsHT = awayTeamGoalsHT;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(String matchDay) {
        this.matchDay = matchDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
