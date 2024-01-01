import java.util.Arrays;
import java.util.PriorityQueue;

public class FindLongestSpecialSubstringOccuringThirceII {
    public int maximumLength(String s) {
        int n = s.length();
        PriorityQueue<Integer>[] pqs = new PriorityQueue[26];
        for (int j = 0; j < 26; ++j) {
            pqs[j] = new PriorityQueue<>();
        }
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            int len = j - i;
            int ind = s.charAt(i) - 'a';
            pqs[ind].offer(len);
            if (pqs[ind].size() > 3) {
                pqs[ind].poll();
            }
            i = j;
        }
        int res = -1;
        for (i = 0; i < 26; ++i) {
            if (!pqs[i].isEmpty()) {
                int v1 = pqs[i].poll();
                int v2 = 0;
                if (!pqs[i].isEmpty()) {
                    v2 = pqs[i].poll();
                }
                int v3 = 0;
                if (!pqs[i].isEmpty()) {
                    v3 = pqs[i].poll();
                }
                //   System.out.println(v1+" "+v2+" "+v3);
                int[] cnt = {v1, v2, v3};
                Arrays.sort(cnt);
                int cur = -1;
                if (cnt[2] == cnt[1] && cnt[1] == cnt[0]) {
                    // 3,3,3
                    cur = cnt[2];
                } else if (cnt[2] - 2 >= cnt[1]) {
                    // 5,2,1
                    cur = cnt[2] - 2;
                } else {
                    // 4,3,1
                    // 3,3,0, or 3,3,1
                    cur = cnt[2] - 1;
                }
                res = Math.max(res, cur);
            }
        }
        return res == 0 ? -1 : res;
    }
}
