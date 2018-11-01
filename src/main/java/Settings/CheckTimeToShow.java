package Settings;

public class CheckTimeToShow {

    public static long start(){
        long start = System.currentTimeMillis();
        return start;
    }

    public static String getTime(long startTime){
        long time = System.currentTimeMillis() - startTime;
        String getTime = "Запрос обработан за " + time + " milliseconds";
        return getTime;
    }

}
