import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JumpGameIx {


    public int[] maxValue(int[] a) {
        int n = a.length;
        int[] minright = new int[n+1];
        minright[n] = Integer.MAX_VALUE;
        for(int i=n-1; i>=0; --i){
            minright[i] = Math.min(minright[i+1], a[i]);
        }
        int maxleft = -1;
        int j = 0;
        int[] res = new int[n];
        for(int i=0; i<n; ++i){
            maxleft = Math.max(maxleft, a[i]);
            if(maxleft <= minright[i+1]){
                for(int k=j; k<=i; ++k){
                    res[k] = maxleft;

                }
                maxleft = -1;
                j = i+1;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new JumpGameIx().maxValue(ArrayUtils.read1d("2,1,3"))));
    }

}


class JumpGameIxMySubmission {
    // aced but...
    private int[] bit;

    public int[] maxValue(int[] a) {
        int n = a.length;
        int[] na = Arrays.copyOf(a, n);
        Arrays.sort(na);
        Map<Integer, Integer> rm = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || na[i] != na[i - 1]) {
                rm.put(na[i], rank++);
            }
        }
        int[] res = Arrays.copyOf(a, n);
        while (true) {
            int[] nres = Arrays.copyOf(res, n);
            int[] maxleft = new int[n];

            maxleft[0] = res[0];
            for (int i = 1; i < n; ++i) {
                maxleft[i] = Math.max(maxleft[i - 1], res[i]);
            }

            bit = new int[rank + 1];
            Arrays.fill(bit, -1);
            for (int i = n - 1; i >= 0; --i) {
                int v = a[i];
                int rv = rm.get(v);
                int cv = q(rv - 1);
                int cur = Math.max(maxleft[i], cv);
                nres[i] = cur;
                u(rv, cur);
            }
            bit = new int[rank + 1];
            Arrays.fill(bit, -1);
            for (int i = 0; i < n; ++i) {
                int v = a[i];
                int rv = rm.get(v);
                int rrv = rank - rv;

                int cv = q(rrv);
                nres[i] = Math.max(nres[i], cv);
                u(rrv, nres[i]);
            }
            if (Arrays.equals(res, nres)) {
                break;
            } else {
                res = nres;
            }
        }


        return res;
    }

    void u(int i, int v) {
        while (i < bit.length) {
            bit[i] = Math.max(bit[i], v);
            i += i & (-i);
        }
    }

    int q(int i) {
        int res = 0;
        while (i > 0) {
            res = Math.max(bit[i], res);
            i -= i & (-i);
        }
        return res;

    }
}