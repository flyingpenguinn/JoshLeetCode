import java.util.TreeMap;

public class PutBoxesIntoWareHouse2 {
    // solve put 1 first from left, then for the residual ones, solve from the right
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < boxes.length; i++) {
            tm.put(boxes[i], tm.getOrDefault(boxes[i], 0) + 1);
        }
        // stuff smallest to the right as much as possible, from left first
        int min = Integer.MAX_VALUE;
        int leftBox = -1;
        int res = 0;
        for (int i = 0; i < warehouse.length; i++) {
            min = Math.min(min, warehouse[i]);
            if (process(tm, min)) {
                leftBox = i;
                res++;
            }
        }
        // process residual from the right
        min = Integer.MAX_VALUE;
        // > leftBox so that we won't step into the territory of the first half
        for (int i = warehouse.length - 1; i > leftBox; i--) {
            min = Math.min(min, warehouse[i]);
            if (process(tm, min)) {
                res++;
            }
        }
        return res;
    }

    private boolean process(TreeMap<Integer, Integer> tm, int min) {
        Integer box = tm.floorKey(min);
        if (box != null) {
            int nv = tm.get(box) - 1;
            if (nv == 0) {
                tm.remove(box);
            } else {
                tm.put(box, nv);
            }
            return true;
        } else {
            return false;
        }
    }
}
