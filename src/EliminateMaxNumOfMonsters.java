import java.util.Arrays;

public class EliminateMaxNumOfMonsters {
    // really confused by "eliminate any monster" during the contest...it means we can choose any one of them but may not be the front one
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            int d = dist[i];
            int s = speed[i];
            int t = (int) Math.ceil(d * 1.0 / s);
            arr[i] = t;
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; ++i) {
            if (arr[i] <= i) {
                return i;
            }
        }
        return n;
    }
}
