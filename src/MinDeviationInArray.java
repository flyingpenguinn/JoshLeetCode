import java.util.*;

public class MinDeviationInArray {
    // each number forms a list of possible numbers
    // we need to find the samllest coverage on them
    // alternatively we can *2 first to convert odd to even, then only decrease numbers using a treeset
    public int minimumDeviation(int[] a) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            List<Integer> ai = new ArrayList<>();
            if (a[i] % 2 == 0) {
                while (a[i] % 2 == 0) {
                    ai.add(a[i]);
                    a[i] /= 2;
                }
                ai.add(a[i]);
                Collections.reverse(ai);
            } else {
                ai.add(a[i]);
                ai.add(a[i] * 2);
            }
            list.add(ai);
        }
        int[] rt = smallestRange(list);
        return rt[1] - rt[0];
    }

    public int[] smallestRange(List<List<Integer>> a) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        int n = a.size();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int v = a.get(i).get(0);
            min = Math.min(min, v);
            max = Math.max(max, v);
            pq.offer(new int[]{v, i, 0});
        }
        int[] r = new int[]{min, max};
        while (true) {
            int[] top = pq.poll();
            int array = top[1];
            int index = top[2];
            int v = top[0];
            if (max - v < r[1] - r[0]) {
                r[0] = v;
                r[1] = max;
            }
            if (index + 1 < a.get(array).size()) {
                int nv = a.get(array).get(index + 1);
                max = Math.max(nv, max);
                pq.offer(new int[]{nv, array, index + 1});
            } else {
                break;
            }
        }
        return r;

    }
}
