public class FindWinnerOfCircularGame {
    // joseph ring...! here the solution is based on 0...n-1, hence +1 at the end
    public int findTheWinner(int n, int k) {
        return find(n, k) + 1;
    }

    private int find(int n, int k) {
        if (n == 1) {
            return 0;
        }
        return (find(n - 1, k) + k) % n;
    }
}
