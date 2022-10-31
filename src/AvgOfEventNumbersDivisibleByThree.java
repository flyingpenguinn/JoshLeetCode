public class AvgOfEventNumbersDivisibleByThree {
    public int averageValue(int[] a) {
        int res = 0;
        int count = 0;
        for (int ai : a) {
            if (ai % 3 == 0 && ai % 2 == 0) {
                res += ai;
                ++count;
            }
        }
        if (count == 0) {
            return 0;
        }
        return res / count;
    }
}
