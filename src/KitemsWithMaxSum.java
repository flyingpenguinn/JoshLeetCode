public class KitemsWithMaxSum {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int ones = Math.min(k, numOnes);
        int zeros = 0;
        int negs = 0;
        k -= ones;
        if (k > 0) {
            zeros = Math.min(k, numZeros);
            k -= zeros;
        }
        if (k > 0) {
            negs = Math.min(k, numNegOnes);
            k -= negs;
        }
        return ones - negs;
    }
}
