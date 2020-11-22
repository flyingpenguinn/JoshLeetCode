import java.util.Arrays;

public class MinimalInitialEnergyToFinishTasks {
    // if we are in shortage, we borrow a[i][1]-energy and spend a[i][0]
    // this means energy += a[i][1]-a[i][0]
    // we want to add as much energy as possible first to reduce the later diffs
    // so sort by the gap = a[i][1]-a[i][0]
    public int minimumEffort(int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(y[1] - y[0], x[1] - x[0]));
        int n = a.length;
        int energy = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (a[i][1] > energy) {
                int borrow = a[i][1] - energy;
                res += borrow;
                energy += borrow;
            }
            energy -= a[i][0];
        }
        return res;
    }

}
