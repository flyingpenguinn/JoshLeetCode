public class MinRangeAfterSumOperations {
    // LC 4117 (biweekly virtual Q2). Either do nothing, or do one op: (x, y, x + y) has range max(x, y).
    // More ops never help: values only grow, and after any op the state is (x, y, x + y) with range max(x, y),
    // where x, y are at least the original values at those two positions. Brute-force checked (BFS, values 1..12).
    public int minRange(int a, int b, int c) {
        int maxv = Math.max(a,Math.max(b,c));
        int minv = Math.min(a, Math.min(b,c));
        int way1 = maxv-minv;
        int way2 = Math.max(a,b);
        int way3 = Math.max(b,c);
        int way4 = Math.max(a,c);
        return Math.min(way1, Math.min(way2, Math.min(way3, way4)));
    }
}
