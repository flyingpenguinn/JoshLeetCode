public class ApplyOperationsToMakeSumGreater {
    // always multiply last
    public int minOperations(int k) {
        int res = k;
        for (int i = 1; i <= k; ++i) {
            int cur1 = i - 1;
            int cur2 = (int) (Math.ceil(k * 1.0 / i)) - 1;
            //   System.out.println(i+" "+cur1+" "+cur2);
            int cur = cur1 + cur2;
            res = Math.min(res, cur);
        }
        return res;
    }
}
