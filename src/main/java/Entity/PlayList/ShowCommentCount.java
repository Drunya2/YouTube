package Entity.PlayList;

import Entity.Snippets.YouTubeAnalytic;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ShowCommentCount {
    public static ResponseComment getInfo(String query, String page) throws UnirestException {
        HttpResponse<ResponseComment> response = Unirest.get("https://www.googleapis.com/youtube/v3/playlistItems")
                .queryString("key", YouTubeAnalytic.API_KEY)
                .queryString("playlistId", query)
                .queryString("maxResults", 50)
                .queryString("pageToken", page)
                .queryString("part", "contentDetails")
                .asObject(ResponseComment.class);
        return response.getBody();
    }
}
