import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinGenerationsToTargetPoint {
    private int code(int[] x) {
        return x[0] * 100 + x[1] * 10 + x[2];
    }

    private int add(int x, int y) {
        int nx = (x / 100 + y / 100) / 2;
        x -= x / 100 * 100;
        y -= y / 100 * 100;
        int ny = (x / 10 + y / 10) / 2;
        x -= x / 10 * 10;
        y -= y / 10 * 10;
        int nz = (x + y) / 2;
        return nx * 100 + ny * 10 + nz;
    }

    public int minGenerations(int[][] points, int[] target) {
        Set<Integer> lastgen = new HashSet<>();
        int ct = code(target);
        for (int[] p : points) {

            int cp = code(p);
            if (cp == ct) {
                return 0;
            }
            lastgen.add(cp);
        }
        for (int k = 1; k <= 20; ++k) {
            int ln = lastgen.size();
            List<Integer> cgen = new ArrayList<>();
            for (int p1 : lastgen) {
                for (int p2 : lastgen) {

                    if (p1 == p2) {
                        continue;
                    }
                    int np = add(p1, p2);
                    if (np == ct) {
                        return k;
                    }
                    cgen.add(np);
                }
            }
            for (int cgi : cgen) {
                if (lastgen.contains(cgi)) {
                    continue;
                }
                lastgen.add(cgi);
            }

        }
        return -1;
    }
}
