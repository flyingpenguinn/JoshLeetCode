import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class RangeModuleRunner {
    public static void main(String[] args) {
        RangeModule rm = new RangeModule();
        rm.addRange(10, 20);
        rm.removeRange(14, 16);
        rm.addRange(1, 2);
        rm.removeRange(0, 3);
        System.out.println(rm.queryRange(10, 14));
        System.out.println(rm.queryRange(13, 15));
        System.out.println(rm.queryRange(16, 17));


        rm = new RangeModule();
        rm.addRange(6, 8);
        rm.removeRange(7, 8);
        rm.removeRange(8, 9);
        rm.removeRange(0, 3);
        rm.addRange(8, 9);
        rm.removeRange(1, 3);
        rm.addRange(1, 8);
        System.out.println(rm.queryRange(2, 4));
        System.out.println(rm.queryRange(2, 9));
        System.out.println(rm.queryRange(4, 6));

    }
}

class RangeModule {
    // keep right as open bracket: this can faciliate the code for merging. we check right<= cur.left and cur.right>=left anyway
    TreeMap<Integer, Integer> tm = new TreeMap<>();

    public RangeModule() {
    }

    public void addRange(int left, int right) {
        int ns = left;
        int ne = right;
        Integer os = getfirst(left);
        while (os != null && os <= right) {
            if (tm.get(os) >= left) {
                ns = Math.min(os, ns);
                ne = Math.max(tm.get(os), ne);
                tm.remove(os);
            }
            os = tm.higherKey(os);
        }
        addto(ns, ne);
        // System.out.println("add " + left+" "+right+" "+tm);
    }

    protected void addto(int ns, int ne) {
        if (ns <= ne) {
            tm.put(ns, ne);
        }
    }

    public boolean queryRange(int left, int right) {
        Integer os = tm.floorKey(left);
        if (os != null) {
            return tm.get(os) >= right;
        } else {
            return false;
        }
    }

    // don't need to worry about merging in remove
    public void removeRange(int left, int right) {
        Integer os = getfirst(left);
        while (os != null && os <= right) {
            if (tm.get(os) >= left) {
                int end = tm.get(os);
                tm.remove(os);
                // beautiful removal!
                addto(os, left);
                addto(right, end);
            }
            os = tm.higherKey(os);
        }
        // System.out.println("remove " + left+" "+right+" "+tm);
    }

    protected Integer getfirst(int left) {
        Integer os = tm.floorKey(left);
        if (os == null) {
            os = tm.ceilingKey(left);
        }
        return os;
    }
}
