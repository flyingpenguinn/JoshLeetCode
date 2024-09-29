import java.util.Arrays;

public class FindLexicographicallySmallestValidSubseq {
    public int[] validSequence(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int j = tn - 1;
        int i = sn - 1;
        int[] last = new int[tn];
        Arrays.fill(last, -1);
        while (j >= 0 && i >= 0) {
            if (t.charAt(j) == s.charAt(i)) {
                last[j] = i;
                --j;
            }
            --i;
        }
        int[] res = new int[tn];
        int skipped = 0;
        for (i = 0, j = 0; i < sn && j < tn; ++i) {
            if (s.charAt(i) == t.charAt(j)) {
                res[j] = i;
                ++j;
            } else if (skipped == 0 && i < last[j + 1]) {
                skipped = 1;
                res[j] = i;
                ++j;
            }
        }
        return j == tn ? res : new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindLexicographicallySmallestValidSubseq().validSequence("vbccca", "abc")));
    }
}
