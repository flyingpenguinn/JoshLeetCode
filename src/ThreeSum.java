import java.util.*;

public class ThreeSum {
    // hold i stable then 2 sum sorted on j and k. o(n2)
    public List<List<Integer>> threeSum(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        List<List<Integer>> r = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0 && a[i] == a[i - 1]) {
                // wont get anything new from a dupe . use this to filter duplication
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                // must be > i,not 0. note we need to filter duplicated j too
                // for example 0,0,0,0. j can be either of the middle 0s
                if (j - 1 > i && a[j - 1] == a[j]) {
                    j++;
                    continue;
                }
                if (a[j] + a[k] == -a[i]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(a[i]);
                    list.add(a[j]);
                    list.add(a[k]);
                    r.add(list);
                    j++;  // k--also works
                } else if (a[j] + a[k] < -a[i]) {
                    j++;
                } else {
                    k--;
                }

            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[] a = {-1, 0, 1, 2, -1, -4};
        System.out.println(new ThreeSum().threeSum(a));
    }
}

//@SILU: xxsum is usually map or sort
// because here i,j,k order is not important, we can sort
// an order-agnostic map would have worked if we allow duplicate
// below approach is very useful when i,j,k order is fixed to be i<j<k, i.e. order matters
class ThreeSumMapBased {
    // hold middle number stable
    public List<List<Integer>> threeSum(int[] a) {
        Arrays.sort(a);
        Set<List<Integer>> r = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            Set<Integer> jset = new HashSet<>();
            for (int j = 0; j < i; j++) {
                jset.add(a[j]);
            }
            for (int k = i + 1; k < a.length; k++) {
                int target = 0 - a[i] - a[k];
                if (jset.contains(target)) {
                    List<Integer> res = new ArrayList<>();
                    res.add(target);
                    res.add(a[i]);
                    res.add(a[k]);

                    r.add(res);
                }
            }
        }
        return new ArrayList<>(r);
    }
}
