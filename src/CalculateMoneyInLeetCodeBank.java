public class CalculateMoneyInLeetCodeBank {
    public int totalMoney(int n) {
        int weeks = (int) Math.floor(n / 7.0); // 10->1
        int day = n % 7; // 10->3
        int monday = (int) Math.ceil(n / 7.0); // 10->2
        // 28,35,42...
        int rw = (28 + 28 + 7 * (weeks - 1)) * weeks / 2;
        int rd = (monday + monday + day - 1) * day / 2;
        return rw + rd;
    }
}
