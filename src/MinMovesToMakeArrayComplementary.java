import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinMovesToMakeArrayComplementary {
    // for each pair, we can at most get it to
    //  1 + Math.min(a[i], a[n - 1 - i])...limit + Math.max(a[i], a[n - 1 - i])  via one move. otherwise it requires two moves
    // so for each t, we know how many pairs need one move, how many need two moves, if we sort the two ends and call binary search on them
    public int minMoves(int[] a, int limit) {
        int n = a.length;
        int[] l1 = new int[n / 2];
        int[] l2 = new int[n / 2];
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n / 2; i++) {
            l1[i] = limit + Math.max(a[i], a[n - 1 - i]);
            l2[i] = 1 + Math.min(a[i], a[n - 1 - i]);
            int sum = (a[i] + a[n - 1 - i]);
            m.put(sum, m.getOrDefault(sum, 0) + 1);
        }
        Arrays.sort(l1);
        Arrays.sort(l2);
        int min = 1000000;
        for (int t = 2; t <= 2 * limit; t++) {
            int pos1 = binaryfirstbigger(l2, t);
            int pos2 = binarylastsmaller(l1, t);
            int bigger = n / 2 - pos1;
            int smaller = pos2 + 1;
            int other = n / 2 - m.getOrDefault(t, 0) - bigger - smaller;
            int res = bigger * 2 + smaller * 2 + other;
            //     System.out.println(t+" "+pos1+" "+pos2+" "+smaller+" "+bigger+" "+other);
            min = Math.min(min, res);
        }
        return min;
    }

    private int binaryfirstbigger(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }

    private int binarylastsmaller(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return u;
    }
}
