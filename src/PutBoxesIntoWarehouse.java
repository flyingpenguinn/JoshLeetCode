import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeMap;

public class PutBoxesIntoWarehouse {
    // basically take big to small boxes and try to stuff them into the warehouse from the left
    public int maxBoxesInWarehouse(int[] boxes, int[] wh) {
        Arrays.sort(boxes);
        int m = boxes.length;
        int n = wh.length;
        int res = 0;
        for (int i = m - 1; i >= 0 & res < n; i--) {
            if (boxes[i] <= wh[res]) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new PutBoxesIntoWarehouse().
                maxBoxesInWarehouse(ArrayUtils.read1d("[4,3,4,1]"), ArrayUtils.read1d("[5,3,3,4,1]")));
    }
}

class PutBoxesToWarehouseAlternative {
    // for each warehouse, find the box. the box must be <= all the current warehouses...so that we can stuff from the farthest
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < boxes.length; i++) {
            tm.put(boxes[i], tm.getOrDefault(boxes[i], 0) + 1);
        }
        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < warehouse.length; i++) {
            min = Math.min(min, warehouse[i]); // key!
            Integer box = tm.floorKey(min);
            if (box != null) {
                res++;
                int nv = tm.get(box) - 1;
                if (nv == 0) {
                    tm.remove(box);
                } else {
                    tm.put(box, nv);
                }
            }

        }
        return res;
    }
}