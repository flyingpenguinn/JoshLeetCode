import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class CarFleet2 {
    // gist is car i catches slower cars ahead of i if that car doesnt clash first with later cars
    public double[] getCollisionTimes(int[][] a) {
        int n = a.length;
        double[] res = new double[n];
        Arrays.fill(res, -1);
        // keep possible collides in the stack
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            // faster cars ahead of i will never be collided
            // if i can't catch them,i-1 can't either
            while (!st.isEmpty() && a[st.peek()][1] >= a[i][1]) {
                st.pop();
            }
            while (!st.isEmpty()) {
                int j = st.peek();
                int toppos = a[j][0];
                int topspeed = a[j][1];
                double t = (toppos - a[i][0] + 0.0) / (a[i][1] - topspeed);
                if (res[j] >= t || res[j] == -1) {
                    // we can catch j before it catches the next car. so j is the man. note we dont worry about faster cars between i and j they are merged with j anyway so we only care about j
                    res[i] = t;
                    break;
                } else {
                    st.pop(); // we can't catch it
                }
            }
            st.push(i);
        }
        return res;
    }
}
