package Entity.Snippets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Channel {

    private String name;
    private String date;
    private long subscribers;
    private int countVideo;
    private long countViews;
    private Date date1;

    public Channel(String name, String date, long subscribers, int countVideo, long countViews) throws ParseException {
        this.name = name;
        this.date = date;
        this.subscribers = subscribers;
        this.countVideo = countVideo;
        this.countViews = countViews;
        this.date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
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

    public Date getDate1() {
        return date1;
    }

    public static Comparator<Channel> ChannelNameComparator = new Comparator<Channel>() {

        public int compare(Channel c1, Channel c2) {
            String ChannelName1 = c1.getName().toUpperCase();
            String ChannelName2 = c2.getName().toUpperCase();

            return ChannelName1.compareTo(ChannelName2);

        }
    };

    public static Comparator<Channel> ChannelDateComparator = new Comparator<Channel>() {

        public int compare(Channel c1, Channel c2) {
            return Long.valueOf(c1.getDate1().getTime()).compareTo(c2.getDate1().getTime());
        }
    };

    public static Comparator<Channel> ChannelSubsComparator = new Comparator<Channel>() {

        public int compare(Channel c1, Channel c2) {

            long countOfSubs1 = c1.getSubscribers();
            long countOfSubs2 = c2.getSubscribers();

            return Long.compare(countOfSubs2, countOfSubs1);
        }
    };

    public static Comparator<Channel> ChannelVideoComparator = new Comparator<Channel>() {

        public int compare(Channel c1, Channel c2) {

            int countOfSubs1 = c1.getCountVideo();
            int countOfSubs2 = c2.getCountVideo();

            return countOfSubs2 - countOfSubs1;
        }
    };

    public static Comparator<Channel> ChannelViewsComparator = new Comparator<Channel>() {

        public int compare(Channel c1, Channel c2) {

            long countOfSubs1 = c1.getCountViews();
            long countOfSubs2 = c2.getCountViews();

            return Long.compare(countOfSubs2, countOfSubs1);
        }
    };
}
