public class ApplyOperationsToMakeAllArrayZero {
    public boolean checkArray(int[] a, int k) {
        int n = a.length;
        int accu = 0;
        for (int i = 0; i < n; ++i) {

            //   System.out.println(a[i]+" "+accu);
            if (accu > a[i]) {
                return false;
            }
            a[i] -= accu;
            accu += a[i];
            if (i - k + 1 >= 0) {
                accu -= a[i - k + 1];
            }
        }
        return accu == 0;
    }
}
