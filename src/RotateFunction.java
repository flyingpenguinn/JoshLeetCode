import base.ArrayUtils;

public class RotateFunction {
    // find relationship between last solution and current one
    public int maxRotateFunction(int[] a) {
        int n = a.length;
        long sum = 0L;
        long last = 0L;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            last += i * a[i];
        }
        long max = last;
        for (int i = 1; i < n; i++) {
            long cur = last + sum - n * a[n - i];
            max = Math.max(max, cur);
            last = cur;
        }
        return (int) max;
    }

    public static void main(String[] args) {
        System.out.println(new RotateFunction().maxRotateFunction(ArrayUtils.read1d("[-2147483648,-2147483648]")));
    }
}
