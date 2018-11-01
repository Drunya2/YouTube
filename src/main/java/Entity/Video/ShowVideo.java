package Entity.Video;

import Entity.PlayList.ResponseComment;
import Entity.Snippets.YouTubeAnalytic;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ShowVideo {

    public static ResponseVideo getInfo(String id) throws UnirestException {
        HttpResponse<ResponseVideo> response = Unirest.get("https://www.googleapis.com/youtube/v3/videos")
                .queryString("key", YouTubeAnalytic.API_KEY)
                .queryString("id", id)
                .queryString("part", "statistics")
                .asObject(ResponseVideo.class);
        return response.getBody();
    }
}
