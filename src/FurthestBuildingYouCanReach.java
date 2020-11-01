import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FurthestBuildingYouCanReach {
    // either put all ladders to pq, then if not enough ladder, change to use brick from the smallest
    // or put all bricks into pq, then if not enough brick, use ladder from the biggest
    public int furthestBuilding(int[] a, int b, int l) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i + 1 < a.length; i++) {
            int diff = a[i + 1] - a[i];
            if (diff > 0) {
                pq.offer(diff);

                if (pq.size() > l) {
                    b -= pq.poll();
                    if (b < 0) {
                        return i;
                    }
                }
            }
        }
        return a.length - 1;
    }
}

class FurthestBuildingYouCanReachAnotherPq {
    public int furthestBuilding(int[] a, int b, int l) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i + 1 < a.length; i++) {
            int diff = a[i + 1] - a[i];
            if (diff > 0) {
                pq.offer(diff);
                b -= diff;
                if (b < 0) {
                    if (l > 0) {
                        b += pq.poll();
                        l--;
                    } else {
                        return i;
                    }
                }
            }
        }
        return a.length - 1;
    }
}

class FurthestBuildingYouCanReachBinarySearch {
    // testing if we can reach is simple...
    public int furthestBuilding(int[] a, int b, int lad) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (cando(a, mid, b, lad)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean cando(int[] a, int mid, int b, int l) {
        List<Integer> gaps = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            if (a[i] < a[i + 1]) {
                gaps.add(a[i + 1] - a[i]);
            }
        }
        Collections.sort(gaps, Collections.reverseOrder());
        for (int i = 0; i < gaps.size(); i++) {
            if (l > 0) {
                l--;
                continue;
            }
            if (gaps.get(i) > b) {
                return false;
            }
            b -= gaps.get(i);
        }
        return true;
    }
}