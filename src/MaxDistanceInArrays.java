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
