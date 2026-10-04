public class MinimumRotationsDialNumberII {
    public int minRotations(int n, String s) {

        int pre = 0;
        int res = 0;
        int[] pref = new int[n];
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int cind = c - '0';
            int cres = getcres(cind, pre);
            res += cres;
            pre = cind;
            pref[i] = res;
        }
        int[] suff = new int[n];
        int allres = res;
        res = 0;
        int last = s.charAt(n - 1) - '0';
        pre = last;
        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            int cind = c - '0';
            int cres = getcres(cind, pre);
            res += cres;
            pre = cind;
            suff[i] = res;
        }
        int allreverse = getcres(last, 0) + res;
        allres = Math.min(allres, allreverse);
        for (int i = 0; i < n - 1; ++i) {
            int res1 = pref[i];
            char c = s.charAt(i);
            int cind = c - '0';
            int res2 = getcres(last, cind);
            int res3 = suff[i + 1];
            int newres = res1 + res2 + res3;
            allres = Math.min(allres, newres);
        }
        return allres;
    }

    private int getcres(int cind, int pre) {
        int way1 = 0;
        int way2 = 0;
        if (cind > pre) {
            way1 = cind - pre;
            way2 = 10 - cind + pre;
        } else {
            way1 = pre - cind;
            way2 = 10 - pre + cind;
        }
        int cres = Math.min(way1, way2);
        return cres;
    }
}
