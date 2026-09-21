import java.util.ArrayList;
import java.util.List;

public class LexicographicallyLargestPowerArray {
    public int[] largestPower(int[] a) {
        int n = a.length;
        int[] res = new int[15];

        List<int[]> bl = new ArrayList<>();
        bl.add(new int[]{0, n});

        for (int bit = 14; bit >= 0; --bit) {
            int cnt = 0;

            for (int b = 0; b < bl.size(); ++b) {
                int[] bi = bl.get(b);
                int l = bi[0];
                int r = bi[1];

                int c1 = 0;
                for (int i = l; i < r; ++i) {
                    if (((a[i] >> bit) & 1) == 1) {
                        ++c1;
                    }
                }

                if (c1 == r - l) {
                    cnt += c1;
                    continue;
                }

                if (c1 == 0) {
                    break;
                }

                int[] tmp = new int[r - l];
                int p = 0;

                for (int i = l; i < r; ++i) {
                    if (((a[i] >> bit) & 1) == 1) {
                        tmp[p++] = a[i];
                    }
                }

                for (int i = l; i < r; ++i) {
                    if (((a[i] >> bit) & 1) == 0) {
                        tmp[p++] = a[i];
                    }
                }

                for (int i = l; i < r; ++i) {
                    a[i] = tmp[i - l];
                }

                cnt += c1;

                bl.set(b, new int[]{l, l + c1});
                bl.add(b + 1, new int[]{l + c1, r});

                break;
            }

            res[14 - bit] = cnt;
        }

        return res;
    }
}
