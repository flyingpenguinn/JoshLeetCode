import java.util.Arrays;

public class CountAlmostEqualPairsI {
    public int countPairs(int[] a) {
        int res = 0;
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (good(a[i], a[j])) {
                    ++res;
                }
            }
        }
        return res;
    }

    private boolean good(int a, int b) {
        if (a == b) {
            return true;
        }
        char[] ca = String.valueOf(a).toCharArray();
        char[] cb = String.valueOf(b).toCharArray();
        return swap(ca, b) || swap(cb, a);
    }

    private boolean swap(char[] a, int b) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                char[] ca = Arrays.copyOf(a, n);
                char tmp = ca[i];
                ca[i] = ca[j];
                ca[j] = tmp;
                String sca = new String(ca);
                int cav = Integer.valueOf(sca);
                if (cav == b) {
                    //     System.out.println(sca + " " + b);
                    return true;
                }
            }

        }
        return false;
    }
}
