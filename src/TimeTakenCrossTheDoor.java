import java.util.TreeSet;

public class TimeTakenCrossTheDoor {
    private int Max = (int) 1e5;

    public int[] timeTaken(int[] arrival, int[] state) {
        int n = arrival.length;
        TreeSet<Integer> enter = new TreeSet<>();
        TreeSet<Integer> leave = new TreeSet<>();
        int j = 0;
        int cstate = 0;
        int[] res = new int[n];
        for (int i = 0; i <= 2 * Max; ++i) {
            while (j < n && arrival[j] <= i) {
                if (state[j] == 0) {
                    enter.add(j);
                } else {
                    leave.add(j);
                }
                ++j;
            }
            int nstate = 0;
            if (cstate == 0) {
                if (!leave.isEmpty()) {
                    int li = leave.pollFirst();
                    res[li] = i;
                    nstate = 2;
                } else if (!enter.isEmpty()) {
                    int ei = enter.pollFirst();
                    res[ei] = i;
                    nstate = 1;
                }
            } else if (cstate == 1) {
                if (!enter.isEmpty()) {
                    int ei = enter.pollFirst();
                    res[ei] = i;
                    nstate = 1;
                } else if (!leave.isEmpty()) {
                    int li = leave.pollFirst();
                    res[li] = i;
                    nstate = 2;
                }
            } else {
                if (!leave.isEmpty()) {
                    int li = leave.pollFirst();
                    res[li] = i;
                    nstate = 2;
                } else if (!enter.isEmpty()) {
                    int ei = enter.pollFirst();
                    res[ei] = i;
                    nstate = 1;
                }
            }
            cstate = nstate;
        }
        return res;
    }
}
