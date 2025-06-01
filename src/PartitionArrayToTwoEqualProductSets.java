public class PartitionArrayToTwoEqualProductSets {
    public boolean checkEqualPartitions(int[] a, long t) {
        int n = a.length;
        int all = (1 << n) - 1;
        for (int st = 1; st < all; ++st) {
            long prod = 1;
            long otherprod = 1;
            for (int i = 0; i < n; ++i) {
                long v = a[i];
                if (((st >> i) & 1) == 1) {
                    prod *= v;
                } else {
                    otherprod *= v;
                }
            }
            if (prod == t && otherprod == t) {
                return true;
            }
        }
        return false;
    }
}
