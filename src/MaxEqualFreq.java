import base.ArrayUtils;

import java.util.*;

public class MaxEqualFreq {
    public int maxEqualFreq(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        Map<Integer, Integer> cm = new HashMap<>();
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            Integer ov = m.get(a[i]);
            int nv = ov == null ? 1 : ov + 1;
            m.put(a[i], nv);
            if (ov != null) {
                int nov = cm.get(ov) - 1;
                if (nov == 0) {
                    cm.remove(ov);
                } else {
                    cm.put(ov, nov);
                }
            }
            cm.put(nv, cm.getOrDefault(nv, 0) + 1);
            if (good(cm)) {
                max = Math.max(max, i + 1);
            }
        }
        return max;
    }

    private boolean good(Map<Integer, Integer> cm) {
        //3 types of numbers, bad
        if (cm.keySet().size() > 2) {
            return false;
        }
        if (cm.keySet().size() == 1) {
            // all 1, can  just remove anyone
            int onlykey = cm.keySet().iterator().next();
            int onlytime = cm.get(onlykey);
            return onlykey == 1 || onlytime == 1;
        } else {
            // 2 types of values.
            List<Integer> keys = new ArrayList<>(cm.keySet());
            Collections.sort(keys);
            Integer key1 = keys.get(0);
            int v1 = cm.get(key1);
            Integer key2 = keys.get(1);
            int v2 = cm.get(key2);
            // diff is 1 and only one item for the bigger one
            if (key2 - 1 == key1 && v2 == 1) {
                return true;
            }
            // value appear once, and only for once, we can remove it
            else if (key1 == 1 && v1 == 1) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new MaxEqualFreq().maxEqualFreq(ArrayUtils.read1d("1, 1, 2, 2, 3, 4, 3")));//7
        System.out.println(new MaxEqualFreq().maxEqualFreq(ArrayUtils.read1d("1,1")));//2
        //7
        System.out.println(new MaxEqualFreq().maxEqualFreq(ArrayUtils.read1d("[1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,42,21,45,27,78,39,78,24,47,60,22,33,45,18,56,91,93,66,79,65,43,7,57,63,74,25,11,14,100,95,19,3,22,18,94,52,91,33,95,16,93,63,65,8,88,51,47,7,51,77,36,48,89,72,81,75,13,69,9,31,16,38,34,76,7,82,10,90,64,28,22,99,40,88,27,94,85,43,75,95,86,82,46,9,74,67,51,93,97,35,2,49]")));//2
        System.out.println(new MaxEqualFreq().maxEqualFreq(ArrayUtils.read1d("1,2")));//2

        System.out.println(new MaxEqualFreq().maxEqualFreq(ArrayUtils.read1d("10,2,8,9,3,8,1,5,2,3,7,6")));//8

        System.out.println(new MaxEqualFreq().maxEqualFreq(ArrayUtils.read1d("1,1,1,2,2,2,3,3,3,4,4,4,5")));//13
        System.out.println(new MaxEqualFreq().maxEqualFreq(ArrayUtils.read1d("[1,1,1,2,2,2]")));//5
        System.out.println(new MaxEqualFreq().maxEqualFreq(ArrayUtils.read1d("[3]")));//1


    }
}
