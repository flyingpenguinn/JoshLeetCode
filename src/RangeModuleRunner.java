import java.util.*;

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
    // just use a sorted list of intervals. they are disjoint too
    TreeSet<int[]> rset = new TreeSet<>((x, y) -> Integer.compare(x[0], y[0]));

    public RangeModule() {

    }

    public void addRange(int left, int right) {
        Iterator<int[]> it = rset.iterator();
        int ml = left;
        int mr = right;
        while (it.hasNext()) {
            int[] item = it.next();
            if (item[1] < ml) {
                continue;
            } else if (item[0] > mr) {
                break;
            } else {
                // this is to union
                ml = Math.min(ml, item[0]);
                mr = Math.max(mr, item[1]);
                it.remove();
            }
        }
        rset.add(new int[]{ml, mr});
    }

    public boolean queryRange(int left, int right) {
        Iterator<int[]> it = rset.iterator();

        while (it.hasNext()) {
            int[] item = it.next();
            if (item[1] < left) {
                continue;
            } else if (item[0] > right) {
                break;
            } else {
                return item[0] <= left && item[1] >= right;
            }
        }
        return false;
    }

    public void removeRange(int left, int right) {
        Iterator<int[]> it = rset.iterator();
        List<int[]> toadd = new ArrayList<>();
        while (it.hasNext()) {
            int[] item = it.next();
            if (item[1] < left) {
                continue;
            } else if (item[0] > right) {
                break;
            } else {
                it.remove();
                if (item[0] < left) {
                    toadd.add(new int[]{item[0], left});
                }
                if (item[1] > right) {
                    // not else, can be surrounding left/right
                    toadd.add(new int[]{right, item[1]});
                }
            }
        }
        // to avoid concurrent modifiation exception
        for (int[] added : toadd) {
            rset.add(added);
        }
    }
}
