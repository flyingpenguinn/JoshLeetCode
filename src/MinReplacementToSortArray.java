import base.ArrayUtils;

public class MinReplacementToSortArray {
    public long minimumReplacement(int[] a) {
        int n = a.length;
        long res = 0;
        int cur = a[n-1];
        for(int i=n-2; i>=0; --i){
            if(a[i]>cur){
                int cv = (int)(Math.ceil(1.0*a[i]/cur));
                res += cv-1;
                cur = a[i]/cv;

            }else{
                cur = a[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinReplacementToSortArray().minimumReplacement(ArrayUtils.read1d("[60,50,22]")));
    }
}
