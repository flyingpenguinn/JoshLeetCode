import java.util.Arrays;

public class MinRoundsToCompleteAllTasks {
    public int minimumRounds(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int i = 0;
        int res = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && a[j] == a[i]) {
                ++j;
            }
            int count = j - i;
            if (count == 1) {
                return -1;
            } else if (count % 3 == 1) {
                res += (count - 4) / 3 + 2;
            } else if (count % 3 == 0) {
                res += (count / 3);
            } else {
                // ==2
                res += (count - 2) / 3 + 1;
            }
            i = j;
        }
        return res;
    }
}
