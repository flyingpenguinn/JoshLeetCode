import java.util.TreeMap;

public class CountIntervals {
    // what a shame not to solve this in VC
    // this is the template for all non overlapping interval merging
    // amortized complexity is nlogn because each internal is put then checked/popped at most once
    private TreeMap<Integer,Integer> tm = new TreeMap<>();
    private int len = 0;
    public CountIntervals() {

    }

    public void add(int left, int right) {
        // search by left!
        var nv = tm.floorEntry(left);
        if(nv == null){
            nv = tm.higherEntry(left);
        }
        int nstart = left;
        int nend = right;
        while(nv != null && nv.getKey()<=right) {
            if (nv.getValue() >= left) {
                nstart = Math.min(nstart, nv.getKey());
                nend = Math.max(nend, nv.getValue());
                tm.remove(nv.getKey());
                len -= nv.getValue() - nv.getKey() + 1;
            }
            // at most one internval not qualifying this if
            nv = tm.higherEntry(nv.getKey());
        }
        tm.put(nstart, nend);
        len += nend-nstart+1;
    }

    public int count() {
        return len;
    }

    public static void main(String[] args) {
        CountIntervals ct = new CountIntervals();
        ct.add(2, 3);
        ct.add(7, 10);
        ct.add(5,8);
        System.out.println(ct.count());
    }
}
