public class NumElapsedTimeBetween {
    private int seconds(String s) {
        String[] sps = s.split(":");
        int hs = Integer.valueOf(sps[0]) * 3600;
        int ms = Integer.valueOf(sps[1]) * 60;
        int ss = Integer.valueOf(sps[2]);
        return hs + ms + ss;
    }

    public int secondsBetweenTimes(String startTime, String endTime) {
        int t1 = seconds(startTime);
        int t2 = seconds(endTime);
        return t2 - t1;
    }
}
