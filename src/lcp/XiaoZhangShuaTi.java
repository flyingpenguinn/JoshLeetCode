package lcp;

import base.ArrayUtils;

import java.util.Collections;
import java.util.PriorityQueue;

public class XiaoZhangShuaTi {
    public int minTime(int[] a, int days) {
        int l = 0;
        int u = 1000000000;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(a, mid, days)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean good(int[] a, int mid, int limit) {
        int cur = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        boolean helped = false;
        int days = 0;
        int i = 0;
        while (i < a.length) {
            if (days == limit) {
                return false;
            } else if (cur + a[i] <= mid) {
                cur += a[i];
                pq.offer(a[i]);
                i++;
            } else {
                if (!helped) {
                    helped = true;
                    int biggest = pq.isEmpty() ? -1 : pq.peek();
                    if (biggest > a[i]) {
                        pq.poll();
                        cur -= biggest;
                        cur += a[i];
                        pq.offer(a[i]);
                    }
                    i++;
                } else {
                    cur = 0;
                    pq.clear();
                    days++;
                    helped = false;
                }
                // else seek help for a[i]
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new XiaoZhangShuaTi().minTime(ArrayUtils.read1d("1,2,3,3"), 2));
        System.out.println(new XiaoZhangShuaTi().minTime(ArrayUtils.read1d("99,99,99"), 4));
        System.out.println(new XiaoZhangShuaTi().minTime(ArrayUtils.read1d("93,97,94,41,55,69,12,7,91,22"), 2));
    }
}
