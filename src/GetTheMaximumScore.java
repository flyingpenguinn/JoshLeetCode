public class GetTheMaximumScore {
    private long Mod = 1000000007;

    public int maxSum(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        long suma = 0;
        long sumb = 0;
        long sum = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                suma += a[i++];
            } else if (a[i] > b[j]) {
                sumb += b[j++];
            } else {
                sum += a[i];
                sum += Math.max(suma, sumb);
                suma = 0;
                sumb = 0;
                i++;
                j++;
            }
        }
        // keep accumulating for one of the arrays if needed
        while (i < a.length) {
            suma += a[i++];
        }
        while (j < b.length) {
            sumb += b[j++];
        }
        sum += Math.max(suma, sumb);
        return (int) (sum % Mod);
    }
}
