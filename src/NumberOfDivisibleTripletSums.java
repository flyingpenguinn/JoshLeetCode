import java.util.HashMap;
import java.util.Map;

public class NumberOfDivisibleTripletSums {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }


    public int divisibleTripletCount(int[] a, int d) {

        Map<Integer, Integer> right = new HashMap<>();
        int n = a.length;
        for (int i = 2; i < n; ++i) {
            int mod = a[i] % d;
            update(right, mod, 1);
        }
        Map<Integer, Integer> left = new HashMap<>();
        update(left, a[0] % d, 1);
        int res = 0;
        for (int i = 1; i + 1 < n; ++i) {
            //    System.out.println("left="+left+" right="+right);
            for (int lk : left.keySet()) {
                int cmod = (a[i] + lk) % d;
                int wanted = (d - cmod) % d;
                int cur = right.getOrDefault(wanted, 0);
                int lefts = left.get(lk);
                res += lefts * cur;
            }
            update(right, a[i + 1] % d, -1);
            update(left, a[i] % d, 1);
        }
        return res;
    }
}
