import java.util.Arrays;

public class MakeArrayElementsEqualToZero {
    public int countValidSelections(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] != 0) {
                continue;
            }
            int[] na = Arrays.copyOf(a, n);
            simu(na, i, 1);
            if (allzero(na)) {
                ++res;
            }
            na = Arrays.copyOf(a, n);
            simu(na, i, -1);
            if (allzero(na)) {
                ++res;
            }
        }
        return res;
    }

    private boolean allzero(int[] a) {
        for (int ai : a) {
            if (ai != 0) {
                return false;
            }
        }
        return true;
    }

    private void simu(int[] a, int i, int id) {
        int n = a.length;
        while (i >= 0 && i < n) {

            if (a[i] == 0) {
                i += id;
            } else {
                a[i] -= 1;
                id = -id;
                i += id;
            }
        }

    }
}
