public class NumberOfGoodBinaryStrings {
    // multiple of certain number: either add this number to extend a section, or change the number!
    // do not need to enumerate how many numbers we are adding
    private long mod = (long) (1e9 + 7);
    private Long[][] dp;
    private int[] counter = new int[2];

    public int goodBinaryStrings(int minl, int maxl, int og, int zg) {
        counter[0] = zg;
        counter[1] = og;
        dp = new Long[maxl + 1][2];
        long rt = solve(0, 0, minl, maxl);
        rt %= mod;
        return (int) rt;
    }

    private long solve(int i, int num, int minl, int maxl) {
        if (i > maxl) {
            return 0;
        }
        if (dp[i][num] != null) {
            return dp[i][num];
        }
        long res = 0;
        if (i >= minl && i <= maxl) {
            res = 1;
        }
        long way1 = solve(i + counter[num], num, minl, maxl); // either keep extending current one
        long way2 = solve(i + counter[num ^ 1], num ^ 1, minl, maxl); // or flip to a new one
        res += way1;
        res += way2;
        res %= mod;
        dp[i][num] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfGoodBinaryStrings().goodBinaryStrings(3, 9, 4, 2));
        System.out.println(new NumberOfGoodBinaryStrings().goodBinaryStrings(2, 3, 1, 2));
        System.out.println(new NumberOfGoodBinaryStrings().goodBinaryStrings(2, 3, 2, 1));

    }
}
