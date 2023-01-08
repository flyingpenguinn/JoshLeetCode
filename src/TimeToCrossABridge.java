import java.util.PriorityQueue;

public class TimeToCrossABridge {
    // very mouthful simulation...
    // worker at rightside always moves first
    // two heap to maintain the waiting list on river bank
    // two heap to maintain the worker list that is puting boxes
    // workers transfer from on river bank to putting boxes

    // total time max: 4e3*e4 = 4e7
    private static final boolean debug = false;

    public int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<Integer> lBank = new PriorityQueue<>((a, b) -> {
            int[] ta = time[a], tb = time[b];
            int ca = ta[0] + ta[2], cb = tb[0] + tb[2];
            if (ca == cb) return b - a; // larger index cross first
            return cb - ca; // larger cross time cross first.
        });
        PriorityQueue<Integer> rBank = new PriorityQueue<>((a, b) -> {
            int[] ta = time[a], tb = time[b];
            int ca = ta[0] + ta[2], cb = tb[0] + tb[2];
            if (ca == cb) return b - a; // larger index cross first
            return cb - ca; // larger cross time cross first.
        });


        // 0 -> time of the worker will be waiting to cross the bridge, 1 ->idx
        PriorityQueue<int[]> lWorker = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> rWorker = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // initally, all at left bank
        for (int i = 0; i < k; i++) lBank.add(i);

        int curTime = 0;
        while (n > 0) {
            // process worker.
            while (!lWorker.isEmpty() && lWorker.peek()[0] <= curTime) lBank.add(lWorker.poll()[1]);
            while (!rWorker.isEmpty() && rWorker.peek()[0] <= curTime) rBank.add(rWorker.poll()[1]);

            int worker = -1;
            if (debug) System.out.println(curTime + " " + lBank);
            if (debug) System.out.println(curTime + " " + rBank);
            if (!rBank.isEmpty()) {
                // right side can pass. A box will be put.
                worker = rBank.poll();
                int t[] = time[worker];
                lWorker.add(new int[]{curTime + t[2] + t[3], worker});
                curTime += t[2]; // right to left.

                n--;
            } else if (!lBank.isEmpty() && (n > rBank.size() + rWorker.size())) {
                // left side can pass.
                // left side only pass when there are more boxes.
                worker = lBank.poll();
                int t[] = time[worker];
                rWorker.add(new int[]{curTime + t[0] + t[1], worker});
                curTime += t[0]; // left to right.
            } else if (n == rBank.size() + rWorker.size()) {
                curTime = rWorker.peek()[0];
            } else {
                // if still empty, advance time.
                int nxt;
                if (rWorker.isEmpty()) nxt = lWorker.peek()[0];
                else if (lWorker.isEmpty()) nxt = rWorker.peek()[0];
                else nxt = Math.min(lWorker.peek()[0], rWorker.peek()[0]);

                curTime = nxt;
            }

            if (debug) System.out.println(curTime + ", " + worker + " n: " + n);
        }

        return curTime;
    }
}
