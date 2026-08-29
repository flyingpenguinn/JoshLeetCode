import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LongestSubarrayWithAtMostKDistinctPrimeFactors {
    static class Prime {
        static boolean[] isprime = null;
        static Set<Integer> primeset = new HashSet<>();
        static List<Integer> primelist = new ArrayList<>();

        private static void getprimes() {
            Arrays.fill(isprime, true);
            isprime[0] = false;
            isprime[1] = false;
            int lim = isprime.length;
            for (long i = 2; i < lim; i++) {
                if (!isprime[(int) i]) {
                    continue;
                }
                for (long j = i * i; j < lim; j += i) {
                    isprime[(int) j] = false;
                }
            }
            for (int i = 0; i < isprime.length; i++) {
                if (isprime[i]) {
                    primelist.add(i);
                    primeset.add(i);
                }
            }
        }


        // for num <= 1e5, at most 6!
        public static  List<Integer> distinctPrimeFactors(int x) {
            List<Integer> res = new ArrayList<>();
            int t = x;

            for (int i = 0; i < primelist.size(); ++i) {
                int p = primelist.get(i);
                if ((long) p * p > t) {
                    break;
                }
                if (t % p == 0) {
                    res.add(p);
                    while (t % p == 0) {
                        t /= p;
                    }
                }
            }

            if (t > 1) {
                res.add(t);
            }

            return res;
        }


        public static void init(int maxNum) {

            if (isprime == null) {
                isprime = new boolean[maxNum + 1];
                getprimes();
            }
        }
    }

    private void update(Map<Integer,Integer> m, int k, int d){
        int nv = m.getOrDefault(k, 0)+d;
        if(nv<=0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }
    public int longestSubarray(int[] a, int k) {
        int n = a.length;
        Map<Integer,Integer> m = new HashMap<>();
        Prime.init(100009);
        int j = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            List<Integer> dps = Prime.distinctPrimeFactors(a[i]);
      //      System.out.println("i="+i+" dps for "+a[i]+ " is "+dps);
            for(int dpi: dps){
                update(m, dpi, 1);
            }
        //     System.out.println("after, m="+m);
            while(j<=i && m.size()>k){
                int head = a[j];
                List<Integer> dpj = Prime.distinctPrimeFactors(head);
        //        System.out.println("dpj for head "+head+" dpj="+dpj);
                for(int dpi: dpj){
                    update(m, dpi, -1);
                }
       //         System.out.println("j="+j+" m="+m);
                ++j;
            }
            int len = i-j+1;
            res = Math.max(res, len);
            
        }
        return res;
    }
}
