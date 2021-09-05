import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GcdSortOfArray {
    // similar to LargestComponentSizeByCommonFactor
    private Map<Integer, Integer> pm = new HashMap<>();// prime factor to its representative
    private  Map<Integer, Integer> parent = new HashMap<>();
    private  Map<Integer, Integer> size = new HashMap<>();
    private boolean[] isp;

    public boolean gcdSort(int[] a) {
        int n = a.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, a[i]);
            parent.put(a[i], a[i]);
            size.put(a[i], 1);
        }
        getprimes(max);
        for (int i = 0; i < n; i++) {

            if (pm.keySet().contains(a[i])) {
                processprime(a[i], a[i]);
            } else {
                for (int j = 2; j * j <= a[i]; j++) {
                    // instead of continuous / of prime numbers this is sqrt(w) and faster....
                    // alternatively we can keep / primes and then move to the next, i.e. factor the number
                    if (isp[j] && a[i] % j == 0) {
                        processprime(a[i], j);
                    }
                    // note this trick so that we only need sqrt(max) steps here
                    int other = a[i] / j;
                    if (isp[other] && other != j && a[i] % other == 0) {
                        processprime(a[i], other);
                    }
                }
            }
        }
        int[] sorted = Arrays.copyOf(a, n);
        Arrays.sort(sorted);
        for(int i=0; i<n; ++i){
            int num1 = sorted[i];
            int num2 = a[i];
            //  System.out.println(num1+"  vs "+num2);
            if(find(num1) != find(num2)){
                // no way we can swap j to position i
                return false;
            }
        }
        return true;
    }

    private boolean issorted(int[] sorted) {
        for(int i=1; i<sorted.length;++i){
            if(sorted[i]<sorted[i-1]){
                return false;
            }
        }
        return true;
    }

    protected void processprime(int v, int prime) {
        int pre = pm.get(prime);
        if (pre == -1) {
            pm.put(prime, v);
        } else {
            union(v, pre);
        }
    }

    private void getprimes(int n) {
        isp = new boolean[n + 1];
        Arrays.fill(isp, true);
        isp[0] = isp[1] = false;
        for (int i = 4; i <= n; i += 2) {
            isp[i] = false;
        }
        for (int i = 3; i * i <= n; i++) {
            if (isp[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isp[j] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isp[i]) {
                pm.put(i, -1);
            }
        }
    }

    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            if (size.get(pa) < size.get(pb)) {
                parent.put(pa, pb);
                size.put(pb, size.get(pb) + size.get(pa));
            } else {
                parent.put(pb, pa);
                size.put(pa, size.get(pa) + size.get(pb));
            }
        }
    }

    private int find(int a) {
        Integer pa = parent.get(a);
        if (pa == a) {
            return a;
        } else {
            int rt = find(pa);
            parent.put(a, rt);
            return rt;
        }
    }
}
