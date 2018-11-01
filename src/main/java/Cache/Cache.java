package Cache;

import Entity.Snippets.Channel;
import Entity.Snippets.CommentsChannel;
import Settings.ComponentSettings;
import Settings.LoadSettings;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.Scanner;

public class Cache {

    public static boolean channelContains(String channelId) throws FileNotFoundException {
        ComponentSettings settings = LoadSettings.loadSettings();
        String path = settings.getPath() + "/Channel" + channelId + ".txt";
        File file = new File(path);
        if (file.exists()) return true;
        else return false;
    }

    public static Channel getChannelFromCache(String channelId) throws FileNotFoundException {
        ComponentSettings settings = LoadSettings.loadSettings();
        String path = settings.getPath() + "/Channel" + channelId + ".txt";
        FileInputStream stream = new FileInputStream(path);
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("\\Z");
        String data = scanner.next();
        scanner.close();
        return JSON.parseObject(data, Channel.class);
    }

    public static void saveToCache(Channel channel,String channelId) throws IOException {
        ComponentSettings settings = LoadSettings.loadSettings();
        String path = settings.getPath() + "/Channel" + channelId + ".txt";
        File file = new File(path);
        FileWriter writer = new FileWriter(path);
        writer.write(JSON.toJSONString(channel));
        writer.flush();
        writer.close();
    }

    public static boolean CommentsContains(String channelId) throws FileNotFoundException {
        ComponentSettings settings = LoadSettings.loadSettings();
        String path = settings.getPath() + "/CommentsChannel" + channelId + ".txt";
        File file = new File(path);
        if (file.exists()) return true;
        else return false;
    }

    public static CommentsChannel getCommentsFromCache(String channelId) throws FileNotFoundException {
        ComponentSettings settings = LoadSettings.loadSettings();
        String path = settings.getPath() + "/CommentsChannel" + channelId + ".txt";
        FileInputStream stream = new FileInputStream(path);
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("\\Z");
        String data = scanner.next();
        scanner.close();
        return JSON.parseObject(data, CommentsChannel.class);
    }

    public static void saveCommentsToCache(CommentsChannel channel,String channelId) throws IOException {
        ComponentSettings settings = LoadSettings.loadSettings();
        String path = settings.getPath() + "/CommentsChannel" + channelId + ".txt";
        File file = new File(path);
        FileWriter writer = new FileWriter(path);
        writer.write(JSON.toJSONString(channel));
        writer.flush();
        writer.close();
    }
}
