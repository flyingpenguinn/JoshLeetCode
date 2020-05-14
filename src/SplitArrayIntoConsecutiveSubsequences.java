import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SplitArrayIntoConsecutiveSubsequences {

    // can use i only when we have more of i than i-1
    // for example 10,10,11,11,11,12,12,12,[13],13,14,14,15,15. we can't "consume" 13 when we are first at 12 because 13 <12 if we we use this 13
    // then later some 12 is not going to be paired with any 13 and we are stuck
    // if there are enough 10,11 then we can pair this 13 with 12 at that time anyway. it's safer not to use that 13 now
    // we can burn i as long as it has >= i-1 nums
    // otherwise we will have prob with dangling i-1
    public boolean isPossible(int[] a) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int ai : a) {
            update(tm, ai, 1);
        }
        while (!tm.isEmpty()) {
            int i = tm.firstKey();
            int c = 0;
            while (tm.containsKey(i) && tm.get(i) - 1 >= tm.getOrDefault(i - 1, 0)) {
                update(tm, i, -1);
                i++;
                c++;
            }
            if (c < 3) {
                return false;
            }
        }
        return true;
    }

    void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public static void main(String[] args) {
        System.out.println(new SplitArrayConsecutiveSubsOn().isPossible(ArrayUtils.read1d("[1,2,3,3,4,5]")));
    }
}

class SplitArrayConsecutiveSubsOn {
    public boolean isPossible(int[] a) {
        int n = a.length;
        Map<Integer, Integer> c = new HashMap<>();
        for (int i = 0; i < n; i++) {
            c.put(a[i], c.getOrDefault(a[i], 0) + 1);
        }
        Map<Integer, Integer> e = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (c.get(a[i]) == null) {
                continue;
            } else if (e.get(a[i] - 1) != null) {
                // we have played out the previous nums. so if we can concat, we continue. starting a new one may fail, not a better option
                update(e, a[i] - 1, -1);
                update(e, a[i], 1);
                update(c, a[i], -1);
            } else {
                if (c.get(a[i] + 1) != null && c.get(a[i] + 2) != null) {
                    update(c, a[i], -1);
                    update(c, a[i] + 1, -1);
                    update(c, a[i] + 2, -1);
                    update(e, a[i] + 2, 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
