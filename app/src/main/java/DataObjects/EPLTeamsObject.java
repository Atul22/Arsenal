package DataObjects;

public class EPLTeamsObject {
    private String name;
    private String shortName;
    private String teamCode;
    private String teamLogo;
    private String teamPlayers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(String teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
}
