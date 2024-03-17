public class FindSumOfEncryptedIntegers {
    public int sumOfEncryptedInt(int[] a) {
        int n = a.length;
        int res = 0;
        for (int ai : a) {
            String si = String.valueOf(ai);
            int max = 0;
            int digits = si.length();
            for (int j = 0; j < si.length(); ++j) {
                max = Math.max(max, si.charAt(j) - '0');
            }
            int cai = 0;
            for (int j = 0; j < si.length(); ++j) {
                cai = cai * 10 + max;
            }
            res += cai;
        }
        return res;
    }
}
