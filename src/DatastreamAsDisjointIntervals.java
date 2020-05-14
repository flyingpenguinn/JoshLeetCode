import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
LC#352
Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]


Follow up:

What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
public class DatastreamAsDisjointIntervals {
    // similar to LC#128 longest consecutive sequence
    public static void main(String[] args) throws IOException {
        SummaryRanges sr = new SummaryRanges();
        String file = "E:\\dev\\project\\JoshLeet\\tests\\disjointinterval";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String seq = reader.readLine();
        String[] ss = seq.split(",");
        for (String s : ss) {
            if (s.equals("[]")) {
                System.out.println(Arrays.deepToString(sr.getIntervals()));
            } else {
                sr.addNum(Integer.valueOf(s.substring(1, s.length() - 1)));
            }
        }
    }
}

class SummaryRanges {
    Map<Integer, Integer> pa = new HashMap<>();
    Map<Integer, Integer> ends = new HashMap<>();
    TreeSet<Integer> roots = new TreeSet<>();

    /**
     * Initialize your data structure here.
     */
    public SummaryRanges() {

    }

    public void addNum(int val) {
        mkroot(val);
        union(val - 1, val);
        union(val, val + 1);
    }

    private void mkroot(int val) {
        if (pa.containsKey(val)) {
            // ! must do this!
            return;
        }
        pa.put(val, val);
        ends.put(val, val);
        roots.add(val);
    }

    private void union(int k1, int k2) {
        Integer p1 = find(k1);
        Integer p2 = find(k2);
        if (p1 == null || p2 == null) {
            return;
        }
        if (p1.equals(p2)) {
            // comparing Integer must use equals!
            return;
        }
        int min = Math.min(p1, p2);
        int max = Math.max(p1, p2);
        pa.put(max, min);
        roots.remove(max);
        ends.put(min, ends.get(max));
    }

    private Integer find(int k) {
        Integer parent = pa.get(k);
        if (parent == null) {
            return null;
        } else if (parent == k) {
            return k;
        } else {
            Integer rt = find(parent);
            pa.put(k, rt);
            return rt;
        }
    }

    public int[][] getIntervals() {
        int[][] r = new int[roots.size()][2];
        int ri = 0;
        for (int k : roots) {
            r[ri++] = new int[]{k, ends.get(k)};
        }
        return r;
    }
}