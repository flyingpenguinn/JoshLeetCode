import java.util.Arrays;

public class EqualSumWithMinNumOps {
    // almost standard two pointer!
    public int minOperations(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int suma = Arrays.stream(a).sum();
        int sumb = Arrays.stream(b).sum();
        if (suma == sumb) {
            return 0;
        }
        if (suma < sumb) {
            return minOperations(b, a);
        }

        // suma>sumb
        int i = a.length - 1;
        int j = 0;
        int diff = suma - sumb;
        int res = 0;

        while ((i >= 0 || j < b.length) && diff > 0) {
            int ca = i < 0 ? 0 : a[i] - 1;
            int cb = j >= b.length ? 0 : 6 - b[j];
            if (ca == 0 && cb == 0) {
                break;
            } else if (ca > cb) {
                res++;
                diff -= Math.min(diff, ca);
                i--;
            } else {
                res++;
                diff -= Math.min(diff, cb);
                j++;
            }
        }

        return diff == 0 ? res : -1;
    }
}

class EqualSumWithMinMoveHeap {
    public int minOperations(int[] a, int[] b) {
        int sa = Arrays.stream(a).sum();
        int sb = Arrays.stream(b).sum();
        if (sa < sb) {
            return minOperations(b, a);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 1) {
                pq.offer(a[i] - 1);
            }
        }
        for (int j = 0; j < b.length; j++) {
            if (b[j] != 6) {
                pq.offer(6 - b[j]);
            }
        }
        int diff = sa - sb;
        int res = 0;
        while (diff > 0 && !pq.isEmpty()) {
            int cur = pq.poll();
            diff -= Math.min(diff, cur);
            res++;
        }
        return diff == 0 ? res : -1;
    }
}
