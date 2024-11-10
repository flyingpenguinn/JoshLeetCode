import java.util.List;

public class AdjacentIncreasingSubarrayDetectionIandII {
    // for I just return res>=k otherwise the same
    public int maxIncreasingSubarrays(List<Integer> a) {
        int n = a.size();
        int i = 0;
        int lastchunk = 0;
        int res = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && a.get(j) > a.get(j - 1)) {
                ++j;
            }
            int len = j - i;
            //     System.out.println(lastchunk+" "+len);
            int cur = Math.min(lastchunk, len);
            res = Math.max(res, cur);
            int half = len / 2;
            res = Math.max(res, half);
            i = j;
            lastchunk = len;
        }
        return res;
    }
}
