package Settings;

import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadSettings {

    public static ComponentSettings loadSettings() throws FileNotFoundException {
        FileInputStream stream = new FileInputStream("src/main/resources/settings/Settings.txt");
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("\\Z");
        String data = scanner.next();
        scanner.close();
        return JSON.parseObject(data, ComponentSettings.class);
    }
}
