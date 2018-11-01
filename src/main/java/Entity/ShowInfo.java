package Entity;

import Entity.Snippets.YouTubeAnalytic;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ShowInfo {
public static ResponseGlobalInfo getInfo(String query) throws UnirestException {
    HttpResponse<ResponseGlobalInfo> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
            .queryString("key", YouTubeAnalytic.API_KEY)
            .queryString("id", query)
            .queryString("part", "snippet,contentDetails,statistics")
            .asObject(ResponseGlobalInfo.class);

    return response.getBody();
}



}
