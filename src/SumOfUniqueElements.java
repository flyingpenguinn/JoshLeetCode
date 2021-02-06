public class SumOfUniqueElements {
    public int sumOfUnique(int[] a) {
        int[] m = new int[101];
        for (int i = 0; i < a.length; i++) {
            m[a[i]]++;
        }
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (m[a[i]] == 1) {
                sum += a[i];
            }
        }
        return sum;
    }
}
