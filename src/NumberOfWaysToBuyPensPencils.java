public class NumberOfWaysToBuyPensPencils {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long n1 = total / cost1;
        long res = 0;
        for (long i = 0; i <= n1; ++i) {
            long cur = ((total - cost1 * i) / cost2 + 1);
            res += cur;
        }
        return res;
    }
}
