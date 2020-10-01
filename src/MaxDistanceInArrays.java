import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class MaxDistanceInArrays {
    // a trick...
    public int maxDistance(List<List<Integer>> a) {
        int min = a.get(0).get(0);
        int max = a.get(0).get(a.get(0).size() - 1);
        int r = 0;
        for (int i = 1; i < a.size(); i++) {
            int cmin = a.get(i).get(0);
            int cmax = a.get(i).get(a.get(i).size() - 1);
            r = Math.max(r, max - cmin);
            r = Math.max(r, cmax - min);
            min = Math.min(min, cmin);
            max = Math.max(max, cmax);
        }
        return r;
    }

    public static void main(String[] args) {
        List<Integer> li = List.of(1);
        List<List<Integer>> l = List.of(li, li);
        System.out.println(new MaxDistanceInArrays().maxDistance(l));
    }
}

class MaxDistanceInArraysEasierUnderstanding {
    // can also use a treemap
    public int maxDistance(List<List<Integer>> a) {
        int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int maxcount = 0;
        int max2count = 0;
        for (int i = 0; i < a.size(); i++) {
            List<Integer> list = a.get(i);
            int curmax = list.get(list.size() - 1);
            if (curmax > max) {
                max2 = max;
                max2count = maxcount;
                max = curmax;
                maxcount = 1;
            } else if (curmax == max) {
                maxcount++;
            } else if (curmax > max2) {
                max2 = curmax;
                max2count = 1;
            } else {
                max2count++;
            }
        }
        int res = 0;
        for (int i = 0; i < a.size(); i++) {
            List<Integer> list = a.get(i);
            int curmin = list.get(0);
            int curmax = list.get(list.size() - 1);
            if (curmax == max && maxcount == 1) {
                res = Math.max(max2 - curmin, res);
            } else {
                res = Math.max(max - curmin, res);
            }
        }
        return res;
    }
}
