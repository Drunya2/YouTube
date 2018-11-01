package Settings;

public class ComponentSettings {
    private  boolean useCacheBool;

    private  boolean showTime;

    private  String path;

    public ComponentSettings(boolean useCacheBool, boolean showTime, String path) {
        this.useCacheBool = useCacheBool;
        this.showTime = showTime;
        this.path = path;
    }

    public boolean isUseCacheBool() {
        return useCacheBool;
    }

    public boolean isShowTime() {
        return showTime;
    }

    public void setUseCacheBool(boolean useCacheBool) {
        this.useCacheBool = useCacheBool;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }


}
