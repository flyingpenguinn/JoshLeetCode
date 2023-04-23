public class SumOfMultipliers {
    public int sumOfMultiples(int n) {
        return solve(n, 3) + solve(n, 5) + solve(n, 7) - solve(n, 15) - solve(n, 35) - solve(n, 21) + solve(n, 105);
    }

    private int solve(int n, int g) {
        int count = n / g;
        if (count == 0) {
            return 0;
        }
        int low = g;
        int high = count * g;
        return (low + high) * count / 2;
    }
}
