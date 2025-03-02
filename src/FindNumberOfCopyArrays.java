public class FindNumberOfCopyArrays {
    public int countArrays(int[] a, int[][] b) {
        int n = a.length;
        int l0 = b[0][0];
        int l1 = b[0][1];
        for (int i = 1; i < n; ++i) {
            int diff = a[i] - a[i - 1];
            int cl0 = l0 + diff;
            int cl1 = l1 + diff;
            cl0 = Math.max(cl0, b[i][0]);
            cl1 = Math.min(cl1, b[i][1]);
            //  System.out.println("l0="+l0 + " l1="+l1+" cl0="+cl0+" cl1="+cl1);
            if (cl0 > cl1) {
                return 0;
            }
            l0 = cl0;
            l1 = cl1;
        }
        return l1 - l0 + 1;
    }
}
