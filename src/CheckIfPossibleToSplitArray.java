import java.util.List;

public class CheckIfPossibleToSplitArray {
    public boolean canSplitArray(List<Integer> a, int m) {
        int n = a.size();
        if (n <= 2) {
            return true;
        }
        for (int i = 0; i + 1 < n; ++i) {
            if (a.get(i) + a.get(i + 1) >= m) {
                return true;
            }
        }
        return false;
    }
}
