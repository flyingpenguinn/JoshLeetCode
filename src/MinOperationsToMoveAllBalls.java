public class MinOperationsToMoveAllBalls {
    public int[] minOperations(String s) {
        int n = s.length();
        int[] res = new int[n];
        int[] bg = new int[n];
        int[] bgs = new int[n];
        int cbg = 0;
        int cbgs = 0;
        for (int i = n - 1; i >= 0; i--) {
            bg[i] = cbg;
            bgs[i] = cbgs;
            if (s.charAt(i) == '1') {
                cbg++;
                cbgs += i;
            }
        }
        int csm = 0;
        int csms = 0;
        for (int i = 0; i < n; i++) {
            //  System.out.println(bgs[i]+" "+csms);
            int cur = bgs[i] - csms;
            if (bg[i] > csm) {
                cur -= (bg[i] - csm) * i;
            } else {
                cur += (csm - bg[i]) * i;
            }
            res[i] = cur;
            if (s.charAt(i) == '1') {
                csm++;
                csms += i;
            }

        }
        return res;
    }
}
