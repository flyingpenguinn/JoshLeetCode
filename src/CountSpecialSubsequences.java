import java.util.*;

public class CountSpecialSubsequences {
    private class Fraction {
        private int a;
        private int b;


        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public Fraction(int a, int b) {
            this.a = a;
            this.b = b;
            int g = gcd(a, b);
            this.a /= g;
            this.b /= g;
        }

        @Override
        public boolean equals(Object o) {
            Fraction fraction = (Fraction) o;
            return a == fraction.a &&
                    b == fraction.b;
        }

        @Override
        public int hashCode() {
            return 10000 * a + b;
        }

        @Override
        public String toString() {
            return a +
                    "/" + b;
        }
    }

    public long numberOfSubsequences(int[] a) {
        int n = a.length;
        Map<Fraction, List<Integer>> fm = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 2; j < n; ++j) {
                Fraction f = new Fraction(a[i], a[j]);
                fm.computeIfAbsent(f, p -> new ArrayList<>()).add(j);
            }
        }
        // System.out.println("fm="+fm);
        for (Fraction k : fm.keySet()) {
            List<Integer> l = fm.get(k);
            Collections.sort(l);
            fm.put(k, l);
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 2; j < n; ++j) {
                Fraction f = new Fraction(a[j], a[i]);
                List<Integer> ls = fm.get(f);
                //   System.out.println("i="+i+" j="+j+" mapped "+ls+" from "+f);
                if (ls != null) {
                    int ln = ls.size();
                    // last num in ls <= a[i]-2
                    int t = i-2;
                    //   System.out.println("binary searching "+t+ " in "+ls);
                    int pos = binary(ls, t);
                    //   System.out.println("found pos="+pos);
                    res += pos + 1;
                }
            }
        }
        return res;
    }

    private int binary(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
