public class SumOfAbsoluteDifference {
    public int[] getSumAbsoluteDifferences(int[] a) {
        int n = a.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        int before = 0;
        int bc = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            before += a[i];
            bc++;
            int after = sum - before;
            int ac = n - bc;
            res[i] = after - before + (bc - ac) * a[i];
        }
        return res;
    }
}
