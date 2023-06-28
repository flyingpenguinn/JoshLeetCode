import java.util.*;

public class RobotCollision {
    class Solution {
        public List<Integer> survivedRobotsHealths(int[] a, int[] b, String ds) {
            int n = a.length;
            int[][] rs = new int[n][4];
            char[] d = ds.toCharArray();
            for (int i = 0; i < n; ++i) {
                rs[i][0] = a[i];
                rs[i][1] = b[i];
                rs[i][2] = i;
                rs[i][3] = d[i] == 'L' ? 1 : 0;
            }
            Arrays.sort(rs, (x, y) -> Integer.compare(x[0], y[0]));
            Deque<int[]> st = new ArrayDeque<>();
            int[] rem = new int[n];
            for (int i = 0; i < n; ++i) {
                int ch = rs[i][1];
                if (rs[i][3] == 1) {
                    while (!st.isEmpty() && ch > 0) {
                        int[] top = st.peek();
                        int th = top[0];
                        if (ch > th) {
                            st.pop();
                            ch -= 1;
                        } else if (ch == th) {
                            st.pop();
                            ch = 0;
                        } else {
                            top[0] -= 1;
                            ch = 0;
                        }
                    }
                    if (ch > 0) {
                        int index = rs[i][2];
                        rem[index] = ch;
                    }
                } else {
                    st.push(new int[]{rs[i][1], rs[i][2]});
                }
            }
            while (!st.isEmpty()) {
                int[] top = st.pop();
                rem[top[1]] = top[0];
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (rem[i] > 0) {
                    res.add(rem[i]);
                }
            }
            return res;
        }
    }
}
