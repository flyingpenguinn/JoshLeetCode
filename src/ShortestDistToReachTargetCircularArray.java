public class ShortestDistToReachTargetCircularArray {
    private int Max = (int) 1e9;

    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int res = Max;
        for (int i = 0; i < n; ++i) {
            if (words[i].equals(target)) {
                int min = Math.min(startIndex, i);
                int max = Math.max(startIndex, i);
                int cur1 = max - min;
                int cur2 = min + n - max;
                int cur = Math.min(cur1, cur2);
                res = Math.min(cur, res);
            }
        }
        return res >= Max ? -1 : res;
    }
}
