public class TotalDistanceTravelled {
    public int distanceTraveled(int a, int b) {
        return solve(a, b);
    }

    private int solve(int a, int b) {
        if (a < 5) {
            return a * 10;
        } else if (b > 0) {
            return 5 * 10 + solve(a - 5 + 1, b - 1);
        } else {
            return a * 10;
        }
    }
}
