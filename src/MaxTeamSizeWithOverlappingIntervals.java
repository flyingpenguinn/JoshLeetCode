import java.util.*;

public class MaxTeamSizeWithOverlappingIntervals {

    public int maximumTeamSize(int[] s, int[] e) {
        int n = s.length;
        List<int[]> ints = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            ints.add(new int[]{s[i], e[i]});
        }
        Arrays.sort(s);
        Arrays.sort(e);

        int res = 0;
        for (int[] i : ints) {
            int cs = i[0];
            int ce = i[1];
            int count1 = binary1(s, ce) + 1;
            int count2 = binary2(e, cs) + 1;
            //    System.out.println(cs+","+ce+"  -- "+count1+".."+count2);
            int cur = count1 - count2;
            res = Math.max(res, cur);
        }
        return res;
    }

    private int binary1(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private int binary2(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] < t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}

class MaxTeamSizeBit {
    private int[] bit;

    private void u(int i, int d) {

        while (i < bit.length) {
            bit[i] += d;
            i += i & (-i);
        }
    }

    private int q(int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    public int maximumTeamSize(int[] s, int[] e) {
        int n = s.length;

        TreeSet<Integer> ts = new TreeSet<>();
        for (int si : s) {
            ts.add(si);
        }
        for (int ei : e) {
            ts.add(ei);
        }
        int rank = 1;
        Map<Integer, Integer> rm = new HashMap<>();
        for (int ti : ts) {
            rm.put(ti, rank++);
        }
        bit = new int[rank];
        List<int[]> ints = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            ints.add(new int[]{rm.get(s[i]), rm.get(e[i])});
        }
        Collections.sort(ints, (x, y) -> Integer.compare(x[1], y[1]));
        for (int i = 0; i < n; ++i) {
            u(ints.get(i)[0], 1);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cs = ints.get(i)[0];
            int ce = ints.get(i)[1];

            int goodstart = q(ce);
            int pos = binary(ints, 0, i, cs);
            // pos is the first index whose end is >= cs. so 0... pos-1 bad
            int bad = pos;
            int cur = goodstart - bad;
            res = Math.max(res, cur);
        }
        return res;
    }

    private int binary(List<int[]> a, int l, int u, int t) {
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid)[1] >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
