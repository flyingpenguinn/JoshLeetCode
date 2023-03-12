import java.util.*;

public class MinTimeToCompleteAllTasks {

    public int findMinimumTime(int[][] tasks) {
        List<int[]> items = new ArrayList<>();
        int res = 0;
        Map<Integer, int[]> active = new HashMap<>();

        for (int i = 0; i < tasks.length; i++) {
            int[] task = tasks[i];
            items.add(new int[]{task[0], 0, task[2], i});
            items.add(new int[]{task[1], 1, task[2], i});
        }

        Collections.sort(items, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            } else if (a[2] != b[2]) {
                return Integer.compare(a[2], b[2]);
            } else {
                return Integer.compare(a[3], b[3]);
            }
        });

        for (int[] item : items) {
            int t = item[0];
            int d = item[1];
            int pt = item[2];
            int i = item[3];

            if (d == 0) {
                //   System.out.println("starting "+i+" "+t+","+pt);
                active.put(i, new int[]{t, pt});
            } else {
                int nonused = active.get(i)[1];
                //   System.out.println("ending "+i+" "+nonused);
                res += nonused;
                if (nonused > 0) {
                    for (int j : active.keySet()) {
                        int[] curr = active.get(j);
                        int use = Math.min(curr[1], Math.min(t - curr[0] + 1, nonused));
                        //     System.out.println(i+" "+use);
                        curr[1] -= use;
                        curr[0] += use;
                    }
                }
                active.remove(i);
            }
        }

        return res;
    }
}
