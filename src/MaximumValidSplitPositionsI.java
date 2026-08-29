public class MaximumValidSplitPositionsI {
    public int maxValidSplits(int[] a) {
        int n = a.length;
        int res = count(a);
        for(int i=0; i<n; ++i){
            int[] na = build(a, i);
            int cur = count(na);
            res = Math.max(res, cur);
        }
        return res;
    }

    private int[] build(int[] a, int skip){
        int n = a.length;
        int[] res = new int[n-1];
        int ri = 0;
        for(int i=0; i<n; ++i){
            if(i==skip){
                continue;
            }
            res[ri++] = a[i];
        }
        return res;
    }

    private int gcd(int a, int b){
        return b==0?a: gcd(b, a%b);
    }

    private int count(int[] a){
        int n = a.length;
        int[] gcdr = new int[n];
        gcdr[n-1] = a[n-1];
        for(int i=n-2; i>=0; --i){
            gcdr[i] = gcd(gcdr[i+1], a[i]);
        }
        int gcdl = a[0];
        int res = 0;
        for(int i=0; i<n-1; ++i){
            gcdl = gcd(gcdl, a[i]);
            if(gcdl == gcdr[i+1]){
                ++res;
            }
        }
        return res;
    }
}
