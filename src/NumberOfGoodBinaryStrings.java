public class NumberOfGoodBinaryStrings {
    // multiple of certain number: either add this number to extend a section, or change the number!
    // do not need to enumerate how many numbers we are adding
    public int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        dp = new Long[maxLength];
        long rt = solve(0, minLength, maxLength, oneGroup, zeroGroup);
        return (int) rt;
    }

    private long Mod = (long) (1e9 + 7);
    private Long[] dp;

    private long solve(int i, int min, int max, int og, int zg) {
        if (i == max) {
            return 1;
        }
        if (i > max) {
            return 0;
        }
        long res = 0;
        if (i >= min) {
            res = 1;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        long way1 = solve(i + og, min, max, og, zg);
        long way2 = solve(i + zg, min, max, og, zg);
        res += way1;
        res += way2;
        res %= Mod;
        dp[i] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfGoodBinaryStrings().goodBinaryStrings(3, 9, 4, 2));
        System.out.println(new NumberOfGoodBinaryStrings().goodBinaryStrings(2, 3, 1, 2));
        System.out.println(new NumberOfGoodBinaryStrings().goodBinaryStrings(2, 3, 2, 1));

    }
}
