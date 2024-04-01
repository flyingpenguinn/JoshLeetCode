public class MinLevelsToGetMostPoints {
    public int minimumLevels(int[] a) {
        int n = a.length;
        int sum = 0;
        for (int ai : a) {
            sum += ai == 1 ? 1 : -1;
        }
        int left = 0;
        int res = n + 1;
        for (int i = 0; i < n - 1; ++i) {
            left += a[i] == 1 ? 1 : -1;
            int right = sum - left;
            //   System.out.println(i+" "+left+" "+right);
            if (left > right) {
                int cur = i + 1;
                res = Math.min(res, cur);
            }
        }
        return res > n ? -1 : res;
    }
}
