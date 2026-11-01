public class MinValueOfMergedIntegers {
    public int minMergedInteger(int[] a) {
        int n = a.length;
        int res = (int)1e9;
        for(int i=0; i+1<n; i += 2){
            String v1 = String.valueOf(a[i]);
            String v2 = String.valueOf(a[i+1]);
            String v = v1+v2;
            int cv = Integer.parseInt(v);
            res = Math.min(res, cv);
        }
        return res;
    }
}
