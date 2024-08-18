import base.ArrayUtils;

import java.util.*;

public class MaxValuePlacingThreeRooksII {
    // what's picked must be top 3 in their row and col
    // pick top 3 in rows
    // pick top 3 in cols
    // pick the intersection, and only take 9 biggest. there must be at least 3 diff cols and 3 different rows here
    private long Min = (long) -1e18;

    public long maximumValueSum(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        HashSet<List<Integer>> rowtops = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            List<List<Integer>> vs = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                vs.add(List.of(a[i][j], i, j));
            }
            Collections.sort(vs, (x, y) -> Integer.compare(y.get(0), x.get(0)));
            for (int j = 0; j < 3; ++j) {
                rowtops.add(vs.get(j));
            }
        }

        HashSet<List<Integer>> coltops = new HashSet<>();
        for (int j = 0; j < n; ++j) {
            List<List<Integer>> vs = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                vs.add(List.of(a[i][j], i, j));
            }
            Collections.sort(vs, (x, y) -> Integer.compare(y.get(0), x.get(0)));
            for (int i = 0; i < 3; ++i) {
                coltops.add(vs.get(i));
            }
        }
     //   System.out.println(coltops);
        Set<List<Integer>> intersect = new HashSet<>(rowtops);
        intersect.retainAll(coltops);
     //   System.out.println(intersect);
        List<List<Integer>> sectlist = new ArrayList<>();
        for (List<Integer> ni : intersect) {
            sectlist.add(ni);
        }
        Collections.sort(sectlist, (x, y) -> Integer.compare(y.get(0), x.get(0)));
        List<List<Integer>> ninelist = new ArrayList<>();
        for(int i=0; i<9; ++i){
            ninelist.add(sectlist.get(i));
        }
        return pick3(ninelist);
    }

    private long pick3(List<List<Integer>> a) {
        int n = a.size();
        long res = Min;

        for (int st = 0; st < (1 << n); ++st) {
            if (Integer.bitCount(st) == 3) {
                Set<Integer> cols = new HashSet<>();
                Set<Integer> rows = new HashSet<>();
                long sum = 0;
                for (int i = 0; i < n; ++i) {
                    if (((st >> i) & 1) == 1) {
                        rows.add(a.get(i).get(1));
                        cols.add(a.get(i).get(2));
                        sum += 0L + a.get(i).get(0);
                    }
                }
                if (rows.size() == 3 && cols.size() == 3) {
                    res = Math.max(res, sum);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MaxValuePlacingThreeRooksII().maximumValueSum(ArrayUtils.read("[[-26,19,59,98],[63,33,20,-23],[37,-26,-44,11],[-52,-61,62,34]]")));
    }
}
