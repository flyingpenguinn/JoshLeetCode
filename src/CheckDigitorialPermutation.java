import java.util.Arrays;

public class CheckDigitorialPermutation {
    private static int[] facts;
    private boolean found = false;

    public boolean isDigitorialPermutation(int n) {
        if (facts == null) {
            facts = new int[10];
            facts[0] = 1;
            for (int i = 1; i <= 9; ++i) {
                facts[i] = facts[i - 1] * i;
            }
        }

        String str = String.valueOf(n);
        char[] sc = str.toCharArray();
        int target = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            int cind = c - '0';
            target += facts[cind];
        }
        String strtarget = String.valueOf(target);
        char[] tc = strtarget.toCharArray();
        Arrays.sort(tc);
        Arrays.sort(sc);
        return Arrays.equals(sc, tc);
    }
}
