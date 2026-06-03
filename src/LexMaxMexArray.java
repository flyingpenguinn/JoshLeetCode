import java.util.ArrayList;
import java.util.List;

public class LexMaxMexArray {
    // suffix then prefix
    public int[] maximumMEX(int[] a) {
        int n = a.length;
        int[] suffix = new int[n];

        int[] cnt = new int[n + 1];
        int lastseen = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (a[i] < cnt.length) {


                ++cnt[a[i]];
            }
            while (cnt[lastseen] > 0) {
                ++lastseen;
            }
            suffix[i] = lastseen;
        }
        int i = 0;
        int[] cnt2 = new int[n + 1];
        lastseen = 0;
        int target = suffix[0];
        List<Integer> res = new ArrayList<>();
        while (i < n) {
            if (a[i] < cnt2.length) {
                ++cnt2[a[i]];
            }
            while (cnt2[lastseen] > 0) {
                ++lastseen;
            }
            if (lastseen == target) {
                res.add(target);
                lastseen = 0;
                if (i + 1 < n) {
                    target = suffix[i + 1];
                    // this is On because the seg must be >=target in length so the sum is <=n
                    cnt2 = new int[target + 1];
                } else {
                    break;
                }
            }
            ++i;
        }
        int[] rres = new int[res.size()];
        for (i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }
}
