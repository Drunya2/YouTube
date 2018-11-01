package Entity.Snippets;

import java.util.Comparator;

public class CommentsChannel {

    private String name;
    private String date;
    private long subscribers;
    private int countVideo;
    private long countViews;
    private long countComments;

    public CommentsChannel(String name, String date, long subscribers, int countVideo, long countViews, long countComments) {
        this.name = name;
        this.date = date;
        this.subscribers = subscribers;
        this.countVideo = countVideo;
        this.countViews = countViews;
        this.countComments = countComments;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public long getSubscribers() {
        return subscribers;
    }

    public int getCountVideo() {
        return countVideo;
    }

    public long getCountViews() {
        return countViews;
    }

    public long getCountComments() {
        return countComments;
    }

    public static Comparator<CommentsChannel> ChannelCommentsComparator = new Comparator<CommentsChannel>() {

        public int compare(CommentsChannel c1, CommentsChannel c2) {

            long countOfSubs1 = c1.getCountComments();
            long countOfSubs2 = c2.getCountComments();

            return Long.compare(countOfSubs2, countOfSubs1);
        }};

}
