import java.util.*;

public class MaxDiffBetweenFreqEvenOddII {
    private int Min = (int) -1e9;
    private int Max = (int) 1e9;

    public int maxDifference(String s, int k) {
        int res = Min;
        int n = s.length();
        char[] c = s.toCharArray();
        for (int a = 0; a <= 4; a++) {
            for (int b = 0; b <= 4; b++) {
                if (a == b) {
                    continue;
                }
                List<Integer> pa = new ArrayList<>();
                List<Integer> pb = new ArrayList<>();
                pa.add(0); // at position -1, both are actually 0
                pb.add(0);
                int j = 0;
                Map<String, Integer> seen = new HashMap<>();
                for (int i = 0; i < n; ++i) {
                    int cind = c[i] - '0';
                    int ca = getback(pa);
                    int cb = getback(pb);
                    if (cind == a) {
                        ++ca;
                    } else if (cind == b) {
                        ++cb;
                    }
                    pa.add(ca);
                    pb.add(cb);
                    // i-k+1... i qualifies. when j=i-k+1 it actually stores data at i-k
                    // two < make sure we have a and b really in this segment
                    while (j <= i - k + 1 && pa.get(j) < ca && pb.get(j) < cb) {
                        int pva = pa.get(j);
                        int pvb = pb.get(j);
                        int ppa = pva % 2;
                        int ppb = pvb % 2;
                        String pkey = ppa + "," + ppb;
                        int pdiff = pva - pvb;
                        if (seen.getOrDefault(pkey, Max) > pdiff) {
                            seen.put(pkey, pdiff);
                        }
                        ++j;
                    }
                    int cpa = ca % 2;
                    int cpb = cb % 2;
                    int lookfora = cpa ^ 1;
                    String lookey = lookfora + "," + cpb;
                    if (seen.containsKey(lookey)) {
                        int pre = seen.get(lookey);
                        int cur = ca - cb - pre;
                        res = Math.max(res, cur);
                    }
                }
            }
        }

        return res;


    }

    private int getback(List<Integer> a) {
        return a.get(a.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new MaxDiffBetweenFreqEvenOddII().maxDifference("12233", 4));
    }

}
