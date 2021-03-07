public class MinElementToAddToFormGivenSum {
    public int minElements(int[] a, int limit, int goal) {
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return (int) Math.ceil(Math.abs(sum - goal) * 1.0 / limit);
    }
}
