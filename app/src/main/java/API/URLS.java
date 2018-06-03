package API;

public class URLS {

    public static String getPageURL(String key, String channelId, String pageToken) {
        return "https://www.googleapis.com/youtube/v3/search?pageToken=" + pageToken +
                "&key=" + key +
                "&channelId=" + channelId + "&part=snippet,id&order=date&maxResults=50";
    }

    public static String getEplURL() {
        return "http://api.football-data.org/v1/competitions/445";
    }

    public static String getEplFixturesURL() {
        return "http://api.football-data.org/v1/competitions/445/fixtures";
    }

    public static String getEplTeamsURL() {
        return "http://api.football-data.org/v1/competitions/445/teams";
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
