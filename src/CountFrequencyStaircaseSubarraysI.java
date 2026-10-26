import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountFrequencyStaircaseSubarraysI {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int countStaircase(int[] a) {
        int n = a.length;
        int res = 0;
        Map<Integer, List<Integer>> occ = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            occ.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
        }

        for (int mlen = 1; mlen * (mlen + 1) <= 2 * n; ++mlen) {

            Map<Integer, Integer> seen = new HashMap<>();
            for (int i = 0; i < n; ++i) {
                int v = a[i];
                update(seen, v, 1);
                int len = mlen * (mlen + 1) / 2;
                int head = i - len + 1;
                if (head >= 0) {
                    if (seen.size() == mlen) {
                        List<int[]> l = new ArrayList<>();
                        for (int k : seen.keySet()) {
                            List<Integer> poss = occ.get(k);
                            int pos = binary(poss, head);
                            if(pos<poss.size()) {
                                l.add(new int[]{poss.get(pos), k});
                            }
                        }
                        Collections.sort(l, (x, y) -> Integer.compare(x[0], y[0]));
                        boolean bad = false;
                        for (int j = 0; j < l.size(); ++j) {
                            int cv = l.get(j)[1];
                            if (seen.get(cv) != j + 1) {
                                bad = true;
                                break;
                            }
                        }
                        if (!bad) {
                            ++res;
                        }
                    }
                    update(seen, a[head], -1);
                }
            }
        }
        return res;
    }

    private int binary(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
