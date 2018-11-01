package Client;

import Controllers.AlertBox;
import Entity.PlayList.ResponseComment;
import Entity.PlayList.ShowCommentCount;
import Entity.ResponseGlobalInfo;
import Entity.ShowInfo;
import Entity.Snippets.Channel;
import Entity.Snippets.CommentsChannel;
import Entity.Video.ResponseVideo;
import Entity.Video.ShowVideo;
import Start.Main;
import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class Requests {

    public static String getDate(String date) {
        String string = new String();

        for (int i = 0; i < 10; i++) {
            string += date.charAt(i);
        }

        return string;
    }

    public static ObservableList<Channel> ShowGlobalInfo(String query) throws UnirestException, ParseException {
        ResponseGlobalInfo globalInfo = ShowInfo.getInfo(query);
        String simpleError = "{\"items\":[]}";                      // Если инфо по запросу нет, выходит такая строка
        String jsonInfo = JSON.toJSONString(globalInfo);
        Platform.runLater(() -> {
            if (simpleError.length() == jsonInfo.length()) AlertBox.display("Убедитеть в правильности запроса!");
        });
        ObservableList<Channel> userData = FXCollections.observableArrayList();
        for (int i = 0; i < globalInfo.items.length; i++) {
            String name = globalInfo.items[i].snippet.title;
            String publishedDate = globalInfo.items[i].snippet.publishedAt;
            String date = (getDate(publishedDate));
            long subscriberCount = Long.parseLong(globalInfo.items[i].statistics.subscriberCount);
            int videoCount = Integer.parseInt(globalInfo.items[i].statistics.videoCount);
            long viewCount = Long.parseLong(globalInfo.items[i].statistics.viewCount);
            userData.add(new Channel(name, date, subscriberCount, videoCount, viewCount));
        }

        return userData;
    }

    public static ObservableList<CommentsChannel> showComments(String query) throws UnirestException, ParseException {
        ObservableList<CommentsChannel> userData = FXCollections.observableArrayList();
        Collections.synchronizedList(userData);
        try {
            ResponseGlobalInfo globalInfo = ShowInfo.getInfo(query);
            String name = "";
            String date = "";
            long subscriberCount = 0;
            int videoCount = 0;
            long viewCount = 0;
            for (int i = 0; i < globalInfo.items.length; i++) {
                name = globalInfo.items[i].snippet.title;
                String publishedDate = globalInfo.items[i].snippet.publishedAt;
                date = (getDate(publishedDate));
                subscriberCount = Long.parseLong(globalInfo.items[i].statistics.subscriberCount);
                videoCount = Integer.parseInt(globalInfo.items[i].statistics.videoCount);
                viewCount = Long.parseLong(globalInfo.items[i].statistics.viewCount);
            }
            ArrayList<String> channelIds = getChannelsIds(globalInfo);
            ArrayList<Long> comments = getComments(channelIds);
            long commentsCount = getAllComments(comments);
            userData.add(new CommentsChannel(name, date, subscriberCount, videoCount, viewCount, commentsCount));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return userData;
    }

    private static ArrayList<String> getChannelsIds(ResponseGlobalInfo globalInfo){
        ArrayList<String> channelIds = new ArrayList<>();
        Collections.synchronizedList(channelIds);
        String playlistId = new String();
        for (int i = 0; i < globalInfo.items.length; i++) {
            playlistId = globalInfo.items[i].contentDetails.relatedPlaylists.uploads;
        }
        String page = "";
        while (page != null) {
            try {
                ResponseComment comment = ShowCommentCount.getInfo(playlistId, page);
                for (int i = 0; i < comment.items.length; i++) {
                    channelIds.add(comment.items[i].contentDetails.videoId);
                    for (int j = 0; j < comment.items.length; j++) {
                        page = comment.nextPageToken;
                    }
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
        return channelIds;
    }

    private static ArrayList<Long> getComments(ArrayList<String> arrayList) {
        ArrayList<Long> comments = new ArrayList<>();
        Collections.synchronizedList(comments);
        for (int k = 0; k < arrayList.size(); k++) {
            try {
                ResponseVideo video = ShowVideo.getInfo(arrayList.get(k));
                for (int i = 0; i < video.items.length; i++) {
                    int index = i;
                    Main.service.submit(() -> {
                        comments.add(Long.parseLong(video.items[index].statistics.commentCount));
                    });
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
        return comments;
    }

    private static long getAllComments(ArrayList<Long> comments){
        long commentsCount = 0;
        for (int i = 0; i < comments.size(); i++) {
            commentsCount += comments.get(i);
        }
        return commentsCount;
    }

}
