import java.util.TreeSet;

public class MaxIncreasingTripletValue {
    public int maximumTripletValue(int[] a) {
        int n = a.length;
        TreeSet<Integer> maxleft = new TreeSet<>();
        int[] maxright = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            maxright[i] = Math.max(a[i], maxright[i + 1]);
        }
        int res = 0;
        maxleft.add(a[0]);
        for (int i = 1; i < n; ++i) {
            Integer ml = maxleft.lower(a[i]);
            if (ml != null && maxright[i + 1] > a[i]) {
                int cur = ml - a[i] + maxright[i + 1];
                res = Math.max(res, cur);
            }
            maxleft.add(a[i]);
        }
        return res;
    }
}
