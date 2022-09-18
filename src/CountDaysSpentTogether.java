public class CountDaysSpentTogether {
    private int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] presum;

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        presum = new int[12];
        for (int i = 1; i < 12; ++i) {
            presum[i] = presum[i - 1] + days[i - 1];
        }
        int ads = getdays(arriveAlice);
        int ade = getdays(leaveAlice);
        int bds = getdays(arriveBob);
        int bde = getdays(leaveBob);
        if (bds > ade || ads > bde) {
            return 0;
        }
        int ds = Math.max(ads, bds);
        int de = Math.min(ade, bde);
        return de - ds + 1;
    }

    private int getdays(String s) {
        String[] ss = s.split("-");
        int month = Integer.valueOf(ss[0]);
        int day = Integer.valueOf(ss[1]);
        return presum[month - 1] + day;
    }
}
