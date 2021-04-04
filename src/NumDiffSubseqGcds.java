public class NumDiffSubseqGcds {
    public int countDifferentSubsequenceGCDs(int[] a) {
        int max = 0;
        for(int i=0; i<a.length; i++){
            max = Math.max(max, a[i]);
        }
        // gcd of all numbers having i as a factor
        int[] fact = new int[max+1];
        for(int i=0; i<a.length; i++){
            for(int j=1; j*j<=a[i]; j++){
                if(a[i]%j==0){
                    fact[j] = gcd(fact[j], a[i]);
                    int rj = a[i]/j;
                    if(rj != j){
                        fact[rj] = gcd(fact[rj], a[i]);
                    }
                }
            }
        }
        int res = 0;
        for(int i=1; i<=max; i++){
            // for i to be contributing, gcd of numbers using i as factor must be ==i. this is to avoid 2 and 4 both taking into account in 4, 16
            if(fact[i]== i){
                res++;
            }
        }
        return res;
    }

    private int gcd(int a, int b){
        if(a<b){
            return gcd(b,a);
        }
        return b==0?a: gcd(b, a%b);
    }
}
