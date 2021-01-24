import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinPeopleToTeach {

    public int minimumTeachings(int n, int[][] ls, int[][] fs) {
        int m = ls.length;
        Set<Integer>[] lset = new HashSet[m + 1];
        for (int i = 0; i < m; i++) {
            lset[i + 1] = new HashSet<>();
            for (int l : ls[i]) {
                lset[i + 1].add(l);
            }
        }
        boolean[] can = new boolean[fs.length];
        for (int j = 0; j < fs.length; j++) {
            int f1 = fs[j][0];
            int f2 = fs[j][1];
            if (intersect(lset[f1], lset[f2])) {
                can[j] = true;
            }
        }
        int res = 10000000;
        for (int i = 1; i <= n; i++) {
            int cur = 0;
            Set<Integer> taught = new HashSet<>();
            for (int j = 0; j < fs.length; j++) {
                int f1 = fs[j][0];
                int f2 = fs[j][1];
                if (!can[j]) {
                    if (!lset[f1].contains(i)) {
                        taught.add(f1);
                    }
                    if (!lset[f2].contains(i)) {
                        taught.add(f2);
                    }
                }
            }
            res = Math.min(res, taught.size());
        }
        return res;
    }

    private boolean intersect(Set<Integer> s1, Set<Integer> s2) {
        for (int s1i : s1) {
            if (s2.contains(s1i)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new MinPeopleToTeach().minimumTeachings(17, ArrayUtils.readUnEven(
                "[[4,7,2,14,6],[15,13,6,3,2,7,10,8,12,4,9],[16],[10],[10,3],[4,12,8,1,16,5,15,17,13],[4,13,15,8,17,3,6,14,5,10],[11,4,13,8,3,14,5,7,15,6,9,17,2,16,12],[4,14,6],[16,17,9,3,11,14,10,12,1,8,13,4,5,6],[14],[7,14],[17,15,10,3,2,12,16,14,1,7,9,6,4]]"),
                ArrayUtils.read("[[4,11],[3,5],[7,10],[10,12],[5,7],[4,5],[3,8],[1,5],[1,6],[7,8],[4,12],[2,4],[8,9],[3,10],[4,7],[5,12],[4,9],[1,4],[2,8],[1,2],[3,4],[5,10],[2,7],[1,7],[1,8],[8,10],[1,9],[1,10],[6,7],[3,7],[8,12],[7,9],[9,11],[2,5],[2,3]]")));
    }
}
