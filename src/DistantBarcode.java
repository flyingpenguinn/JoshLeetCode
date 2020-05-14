import base.ArrayUtils;

import java.util.*;

/*
LC#1054
In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.



Example 1:

Input: [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,2,1,2,1]


Note:

1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000

 */
public class DistantBarcode {

    // high freq at 0,2,4...first then next high.
    // note wiggle sort like solution won't work
    public int[] rearrangeBarcodes(int[] a) {
        int n = a.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[1], x[1]));

        Map<Integer, Integer> fm = new HashMap<>();
        for (int ai : a) {
            fm.put(ai, fm.getOrDefault(ai, 0) + 1);
        }
        for (int k : fm.keySet()) {
            pq.offer(new int[]{k, fm.get(k)});
        }
        int[] r = new int[n];
        int rp = 0;
        while (!pq.isEmpty()) {
            int[] ele = pq.poll();
            int en = ele[0];
            int ef = ele[1];
            while (ef > 0) {
                r[rp] = en;
                ef--;
                rp += 2;
                if (rp >= n) {
                    rp = 1;
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DistantBarcode().rearrangeBarcodes(ArrayUtils.read1d("2,2,2,1,5"))));
        System.out.println(Arrays.toString(new DistantBarcode().rearrangeBarcodes(ArrayUtils.read1d("1,1,2"))));
        System.out.println(Arrays.toString(new DistantBarcode().rearrangeBarcodes(ArrayUtils.read1d("1,1,2,2,3,3"))));
        System.out.println(Arrays.toString(new DistantBarcode().rearrangeBarcodes(ArrayUtils.read1d("1,1,1,1,2,2,3,3"))));
        System.out.println(Arrays.toString(new DistantBarcode().rearrangeBarcodes(ArrayUtils.read1d("1,1,1,2,2,2"))));
    }
}
