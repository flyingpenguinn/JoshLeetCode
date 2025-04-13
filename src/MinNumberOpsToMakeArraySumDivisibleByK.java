public class MinNumberOpsToMakeArraySumDivisibleByK {
    public int minOperations(int[] a, int k) {
        int n = a.length;
        int sum = 0;
        for (int ai : a) {
            sum += ai;
        }
        return sum % k;
    }
}
