package YouTubeAPI;

public class URLS {

    public static String getPageURL(String key, String channelId, String pageToken) {
        return "https://www.googleapis.com/youtube/v3/search?pageToken=" + pageToken +
                "&key=" + key +
                "&channelId=" + channelId + "&part=snippet,id&order=date";
    }
}
