public class MaxDifferenceRemapDigit {
    public int minMaxDifference(int num) {
        String sn = String.valueOf(num);
        int min = (int) 2e9;
        int max = 0;
        for (int digit = 0; digit <= 9; ++digit) {
            for (int nd = 0; nd <= 9; ++nd) {
                String nsn = sn.replaceAll(String.valueOf(digit), String.valueOf(nd));
                int nv = Integer.valueOf(nsn);
                min = Math.min(min, nv);
                max = Math.max(max, nv);
            }
        }
        return max - min;
    }
}
