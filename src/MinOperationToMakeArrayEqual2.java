public class MinOperationToMakeArrayEqual2 {
    public long minOperations(int[] a, int[] b, int k) {
        int n = a.length;
        long suma = 0;
        long sumb = 0;
        for(int ai: a){
            suma += ai;
        }
        for(int bi: b){
            sumb += bi;
        }
        if(suma != sumb){
            return -1;
        }
        long diffs = 0;
        for(int i=0; i<n; ++i){
            long diff = Math.abs(a[i]-b[i]);
            diffs += diff;
        }

        if(k==0 ){
            return diffs>0? -1: 0;
        }
        if(diffs % (2*k) != 0){
            return -1;
        }
        return diffs/(2*k);
    }
}
