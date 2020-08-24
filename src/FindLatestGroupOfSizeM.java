import java.util.TreeSet;

public class FindLatestGroupOfSizeM {
    // 0 positions are in treeset. work backwards. if there are m slots between 0s then we found it. between 0s there are all 1s
    public int findLatestStep(int[] a, int m) {
        int n = a.length;
        // don't forget to check the last: 0 vs n+1 there are n ones in between
        if (m == n) {
            return n;
        }
        TreeSet<Integer> tset = new TreeSet<>();
        tset.add(0);
        tset.add(n + 1);
        for (int i = n - 1; i >= 0; i--) {
            int cur = a[i];
            Integer lower = tset.lower(cur);
            if (lower != null && cur - lower - 1 == m) {
                return i;
            }
            Integer higher = tset.higher(cur);
            if (higher != null && higher - cur - 1 == m) {
                return i;
            }
            tset.add(cur);
        }
        return -1;
    }
}

class FindLatestGroupOfSizeMOn {
    // go from front to back and merge
    public int findLatestStep(int[] a, int m) {
        int n = a.length;
        // len of consecutive 1s of the block i is in. note it's only accurate when i is left or right end point
        int[] len = new int[n + 2];
        int[] count = new int[n + 1];
        int res = -1;
        for (int i = 0; i < n; i++) {
            int cur = a[i];
            // cur must be a 0, so cur-1 and cur+1 are accurate about the block size
            int left = len[cur - 1]; // left and right are lengths
            int right = len[cur + 1];
            int newlen = left + right + 1;
            len[cur] = len[cur - left] = len[cur + right] = newlen;
            count[left]--;
            count[right]--;
            count[newlen]++;
            if (count[m] > 0) {
                res = i + 1;
            }
        }
        return res;
    }
}
