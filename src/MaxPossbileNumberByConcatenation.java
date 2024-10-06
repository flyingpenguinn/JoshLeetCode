public class MaxPossbileNumberByConcatenation {
    public int maxGoodNumber(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    if (i == k || j == k) {
                        continue;
                    }
                    StringBuilder sn = new StringBuilder();
                    String s1 = Integer.toBinaryString(a[i]);
                    String s2 = Integer.toBinaryString(a[j]);
                    String s3 = Integer.toBinaryString(a[k]);
                    sn.append(s1);
                    sn.append(s2);
                    sn.append(s3);
                    String ss = sn.toString();
                    //System.out.println(ss);
                    int cur = Integer.parseInt(ss, 2);
                    res = Math.max(res, cur);
                }
            }

        }
        return res;
    }
}
