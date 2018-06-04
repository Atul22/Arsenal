package API;

public class URLS {

    public static String getPageURL(String key, String channelId, String pageToken) {
        return "https://www.googleapis.com/youtube/v3/search?pageToken=" + pageToken +
                "&key=" + key +
                "&channelId=" + channelId + "&part=snippet,id&order=date&maxResults=50";
    }

    public static String getLeagueCompetetionUrl() {
        return "http://api.football-data.org/v1/competitions/";
    }

    public static String getLeagueURL(String competetionId) {
        return "http://api.football-data.org/v1/competitions/" + competetionId;
    }

    public static String getLeagueFixturesURL(String competetionId) {
        return "http://api.football-data.org/v1/competitions/" + competetionId + "/fixtures";
    }

    public static String getLeagueTeamsURL(String competetionId) {
        return "http://api.football-data.org/v1/competitions/" + competetionId + "/teams";
    }


    public static String getNewsfeedUrl(String key, String date) {
        return "https://newsapi.org/v2/everything?sources=bbc-sport&q=football&from=" + date + "&sortBy=publishedAt&apiKey=" + key;
    }

    public static String getPlayListItemsURL(String pageToken, String playListId, String key) {
        return "https://www.googleapis.com/youtube/v3/playlistItems?pageToken=" + pageToken +
                "&part=snippet&maxResults=50&order=date&playlistId=" + playListId +
                "&key=" + key;
    }
}
