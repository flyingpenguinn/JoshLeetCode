public class FindArrayConcatValue {
    public long findTheArrayConcVal(int[] a) {
        int l = 0;
        int u = a.length - 1;
        long res = 0;
        while (l <= u) {
            long cur = 0;
            if (l == u) {
                cur = a[l];
            } else {
                cur = Long.valueOf(a[l] + "" + a[u]);
            }
            //  System.out.println(cur);
            res += cur;
            ++l;
            --u;
        }
        return res;
    }
}
