package API;

public class URLS {

    public static String getPageURL(String key, String channelId, String pageToken) {
        return "https://www.googleapis.com/youtube/v3/search?pageToken=" + pageToken +
                "&key=" + key +
                "&channelId=" + channelId + "&part=snippet,id&order=date&maxResults=20";
    }

    public static String getEplURL() {
        return "http://api.football-data.org/v1/competitions/445";
    }
}
