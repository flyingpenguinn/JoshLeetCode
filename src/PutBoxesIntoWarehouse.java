import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeMap;

public class PutBoxesIntoWarehouse {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < boxes.length; i++) {
            tm.put(boxes[i], tm.getOrDefault(boxes[i], 0) + 1);
        }
        int res = 0;
        // need min here because we can't get a bigger box after a small warewhouse
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < warehouse.length; i++) {
            min = Math.min(min, warehouse[i]);
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

    public static void main(String[] args) {
        System.out.println(new PutBoxesIntoWarehouse().
                maxBoxesInWarehouse(ArrayUtils.read1d("[4,3,4,1]"), ArrayUtils.read1d("[5,3,3,4,1]")));
    }
}
