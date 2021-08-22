import java.util.*;

public class FindArrayGivenSubsetSums {
    private int[] res;
    private int ri = 0;

    // try to find one element that is in the array. then take the element out and recurse
    public int[] recoverArray(int n, int[] a) {
        res = new int[n];
        List<Integer> cur = new ArrayList<>();
        for (int ai : a) {
            cur.add(ai);
        }
        solve(cur, n);
        return res;
    }

    private void solve(List<Integer> a, int elements) {
        Collections.sort(a);
        //      System.out.println(a);
        if (a.size() == 2) {
            res[ri++] = a.get(1) == 0 ? a.get(0) : a.get(1);
            // .get(0) must be 0
            return;
        }
        int n = a.size();
        int num = a.get(n - 1) - a.get(n - 2);
        // either num of -num is in the set.  because it could be all positive without the smallest pos, or with the biggest neg. so num or -num is the answer
        Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            update(m, ai, 1);
        }
        // as if it's num, we divide the array to containing num vs not containing num. containing means a[i]-num is also in the array
        List<Integer> without = new ArrayList<>();
        List<Integer> with = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (m.containsKey(a.get(i))) {
                with.add(a.get(i));
                without.add(a.get(i) - num);
                update(m, a.get(i), -1);
                update(m, a.get(i) - num, -1);
            }
        }
        boolean found = false;
        for (int i = 0; i < with.size(); i++) {
            if (with.get(i) == num) {
                if (without.get(i) == 0) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            res[ri++] = num;
            // if it's num, we decrase it so effectively taking without
            solve(without, elements - 1);
        } else {
            // otherwise we should take -num which is effectively +-num, and it's the with that we calced
            res[ri++] = -num;
            solve(with, elements - 1);
        }
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
