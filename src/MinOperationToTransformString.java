public class MinOperationToTransformString {

    public int minOperations(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            int cur = 0;
            if (cind > 0) {
                cur = 26 - cind;
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
