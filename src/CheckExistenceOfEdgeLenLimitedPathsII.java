import base.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class CheckExistenceOfEdgeLenLimitedPathsII {
    // store the snapshot! here each time we query with certain limit we note in a treemap that this limit caused a pa change. then for each query we search for floor
    // logn^2 in searching and nlgn^2 to init
    private static class DistanceLimitedPathsExist {
        private TreeMap<Integer, Integer>[] pa;
        public DistanceLimitedPathsExist(int n, int[][] es) {
            List<int[]> el = new ArrayList<>();
            for (int[] e : es) {
                el.add(new int[]{e[0], e[1], e[2]});
            }
            Collections.sort(el, (x, y) -> Integer.compare(x[2], y[2]));
            pa = new TreeMap[n];
            for (int i = 0; i < n; i++) {
                pa[i] = new TreeMap<>();
                pa[i].put(0, i);
            }
            for (int i = 0; i < el.size(); i++) {
                int s = el.get(i)[0];
                int e = el.get(i)[1];
                int d = el.get(i)[2];
                int ps = find(s, d);
                int pe = find(e, d);
                if (ps != pe) {
                    pa[ps].put(d, pe);
                }
            }
        }

        private int find(int i, int limit) {
            int last = pa[i].floorKey(limit);
            int father = pa[i].get(last);
            if (father == i) {
                return i;
            }
            int rt = find(father, limit);
            pa[i].put(limit, rt);
            return rt;
        }


        public boolean query(int p, int q, int limit) {
            int pp = find(p, limit - 1);
            int pq = find(q, limit - 1);
            return pp == pq;
        }
    }

    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\checkpathexistence";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String seq = reader.readLine();
        DistanceLimitedPathsExist path2 = new DistanceLimitedPathsExist(85, ArrayUtils.read(seq));
        path2.query(47, 64, 2);
    }
}
