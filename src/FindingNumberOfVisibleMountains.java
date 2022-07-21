import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FindingNumberOfVisibleMountains {
    // depending on x> or < the criteria for containing is different
    public int visibleMountains(int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int n = a.length;
        int res = 0;
        Deque<Integer> st = new ArrayDeque<>();
        int i = 0;
        while (i < n) {
            // weed out the same ones
            int j = i;
            while (j < n && Arrays.equals(a[i], a[j])) {
                ++j;
            }
            if (j - i > 1) {
                i = j;
            }
            if (i == n) {
                break;
            }
            int x = a[i][0];
            int y = a[i][1];
            while (!st.isEmpty() && y - a[st.peek()][1] >= x - a[st.peek()][0]) {
                // previous one falling on the left half of current
                st.pop();
            }
            if (!st.isEmpty() && a[st.peek()][1] - y >= x - a[st.peek()][0]) {
                // current one falling on the right half of last one
                ++i;
                continue;
            }
            st.push(i);
            ++i;
        }
        return st.size();
    }
}
