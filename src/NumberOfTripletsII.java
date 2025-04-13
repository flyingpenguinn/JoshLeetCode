public class NumberOfTripletsII {
    //at most distinct xors can be 2047 which can be max by doing(1 ^ 1500).
    public int uniqueXorTriplets(int[] a) {
        int n = a.length;
        int lim = 2050;
        int[] threes = new int[lim];
        int[] twos = new int[lim];
        int[] ones = new int[lim];
        ones[a[0]] = 1;
        for (int i = 0; i < n; ++i) {
            for(int j=0; j<lim; ++j){
                if(ones[j] == 0){
                    continue;
                }
                twos[a[i]^j] = 1;
            }
            for(int j=0; j<lim; ++j){
                if(twos[j] == 0){
                    continue;
                }
                threes[a[i]^j] = 1;
            }
            ones[a[i]]= 1;
        }
        int res = 0;
        for(int ti: threes){
            res += ti;
        }
        return res;
    }
}
