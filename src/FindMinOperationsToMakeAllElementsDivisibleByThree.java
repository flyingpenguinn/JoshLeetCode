public class FindMinOperationsToMakeAllElementsDivisibleByThree {
    public int minimumOperations(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int mod = a[i] % 3;
            if (mod == 0) {
                continue;
            } else if (mod == 1) {
                res += 1;
            } else if (mod == 2) {
                res += 1;
            }
        }
        return res;
    }
}
