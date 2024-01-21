import java.util.Arrays;

public class MinNumberOfPushesToTypeWordIAndII {
    public int minimumPushes(String word) {
        int[] c = new int[26];
        for (char ch : word.toCharArray()) {
            int cind = ch - 'a';
            ++c[cind];
        }
        Arrays.sort(c);
        int keys = 8;
        int count = 1;
        int res = 0;
        for (int i = 25; i >= 0; --i) {
            if (c[i] == 0) {
                break;
            }
            res += count * c[i];
            --keys;
            if (keys == 0) {
                keys = 8;
                ++count;
            }
        }
        return res;
    }
}
