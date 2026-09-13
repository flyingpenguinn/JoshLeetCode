import java.util.List;
import java.util.TreeSet;

public class MinOperationsToMakeEveryElementPalindromic {
    private static TreeSet<Integer> oddpalin;
    private static TreeSet<Integer> evenpalin;
    private long Max = (long) 1e18;

    public long minOperations(int[] a) {
        int n = a.length;
        if (oddpalin == null) {
            oddpalin = new TreeSet<>();
            evenpalin = new TreeSet<>();
            for (int d = 1; d <= 9; ++d) {
                genpalin(d);
            }
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int cv = a[i];
            long cur = Max;
            if(cv%2==1) {
                Integer lower = oddpalin.ceiling(cv);
                Integer higher = oddpalin.floor(cv);
                if (lower != null) {
                    cur = Math.min(cur, Math.abs(cv - lower)/2);
                }
                if (higher != null) {
                    cur = Math.min(cur, Math.abs(cv - higher)/2);
                }
            }else{
                Integer lower = evenpalin.ceiling(cv);
                Integer higher = evenpalin.floor(cv);
                if (lower != null) {
                    cur = Math.min(cur, Math.abs(cv - lower)/2);
                }
                if (higher != null) {
                    cur = Math.min(cur, Math.abs(cv - higher)/2);
                }
            }
            res += cur;
        }
        return res;
    }

    private Integer binary(List<Integer> list, int v) {
        int l = 0;
        int u = list.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (list.get(mid) <= v) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private static void genpalin(int d) {
        if (d == 1) {
            for (int i = 1; i <= 9; ++i) {
                if(i%2==0){
                    evenpalin.add(i);
                }else {
                    oddpalin.add(i);
                }
            }
            return;
        }
        int half = d / 2;
        int start = (int) Math.pow(10, half - 1);
        int end = (int) Math.pow(10, half) - 1;
        for (int i = start; i <= end; ++i) {
            String si = String.valueOf(i);
            String rev = new StringBuilder(si).reverse().toString();
            if (d % 2 == 1) {
                for (int mid = 0; mid <= 9; ++mid) {
                    String ci = si + mid + rev;
                    int civ = Integer.valueOf(ci);
                    if(civ%2==0){
                        evenpalin.add(civ);
                    }else{
                        oddpalin.add(civ);
                    }
                }
            }else{
                String ci = si +  rev;
                int civ = Integer.valueOf(ci);
                if(civ%2==0){
                    evenpalin.add(civ);
                }else{
                    oddpalin.add(civ);
                }
            }
        }
    }
}
