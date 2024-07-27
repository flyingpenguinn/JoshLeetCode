import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class MinIncreasingSubseqToBeRemoved {
    // same as longest non increasing subseq
    // which is reversed longest increasing subseq
    public int minOperations(int[] a) {
        int n = a.length;
        for (int i = 0; i < n / 2; ++i) {
            int tmp = a[i];
            a[i] = a[n - 1 - i];
            a[n - 1 - i] = tmp;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int pos = binary(list, a[i]);
            if (pos == list.size()) {
                list.add(a[i]);
            } else {
                list.set(pos, a[i]);
            }
        }
        return list.size();
    }

    private int binary(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) > t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}


class MinSubseqRemovedII {
    // this can totally solve LIS problem once we reverse the array
    public int minOperations(int[] a) {
        int n = a.length;
        TreeMap<Integer, TreeSet<Integer>> tm = new TreeMap<>();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            Integer smaller = tm.lowerKey(a[i]);
            if (smaller != null) {
                TreeSet<Integer> indexes = tm.get(smaller);
                indexes.pollFirst();
                if (indexes.isEmpty()) {
                    tm.remove(smaller);
                }
            } else {
                ++res;
            }
            tm.computeIfAbsent(a[i], k -> new TreeSet<>()).add(i);
        }
        return res;
    }
}