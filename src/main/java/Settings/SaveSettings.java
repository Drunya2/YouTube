package Settings;

import com.alibaba.fastjson.JSON;

import java.io.FileWriter;
import java.io.IOException;

public class SaveSettings {

    public static void saveSettings(ComponentSettings settings) throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/settings/Settings.txt");
        String str = JSON.toJSONString(settings);
        writer.write(str);
        writer.flush();
        writer.close();
    }
}
