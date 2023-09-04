public class MinOperationsToMakeSpecialNumber {
    public int minimumOperations(String num) {
        int n = num.length();
        int res = n;
        for (int i = n - 1; i >= 0; --i) {
            int ni = num.charAt(i) - '0';
            int d1 = n - 1 - i;
            for (int j = i - 1; j >= 0; --j) {
                int nj = num.charAt(j) - '0';
                int d2 = i - j - 1;
                int cn = nj * 10 + ni;
                //    System.out.println(cn);
                if (cn % 25 == 0) {
                    int cur = d1 + d2;
                    res = Math.min(res, cur);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (num.charAt(i) == '0') {
                res = Math.min(res, n - 1);
            }
        }
        return res;
    }
}
