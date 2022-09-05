import java.util.Arrays;

public class CheckDistanceBetweenSameLetters {
    public boolean checkDistances(String s, int[] d) {
        int n = s.length();
        int[] pre = new int[26];
        Arrays.fill(pre, -1);
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            if (pre[cind] == -1) {
                pre[cind] = i;
            } else {
                int cd = i - pre[cind] - 1;
                if (cd != d[cind]) {
                    return false;
                }
            }
        }
        return true;
    }
}
