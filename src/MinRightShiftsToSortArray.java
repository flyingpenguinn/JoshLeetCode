import java.util.List;

public class MinRightShiftsToSortArray {
    public int minimumRightShifts(List<Integer> a) {
        int n = a.size();
        int i = 0;
        while (i + 1 < n && a.get(i) < a.get(i + 1)) {
            ++i;
        }
        if (i == n - 1) {
            return 0;
        }
        int res = n - 1 - i;
        ++i;

        while (i + 1 < n && a.get(i) < a.get(i + 1)) {
            ++i;
        }
        if (i == n - 1) {
            return a.get(i) < a.get(0) ? res : -1;
        } else {
            return -1;
        }
    }
}
