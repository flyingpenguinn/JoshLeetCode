import base.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QueensAttackKing {

    // start from the king
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {

        Set<List<Integer>> queenset = new HashSet<>();
        for (int[] q : queens) {
            queenset.add(make(q[0], q[1]));
        }
        List<List<Integer>> r = new ArrayList<>();
        int kr = king[0];
        int kc = king[1];
        for (int[] d : dirs) {
            int nr = kr;
            int nc = kc;
            while (nr < 8 && nr >= 0 && nc < 8 && nc >= 0) {
                nr += d[0];
                nc += d[1];
                List<Integer> coord = make(nr, nc);
                if (queenset.contains(coord)) {
                    r.add(coord);
                    break;
                }
            }
        }
        return r;
    }

    private List<Integer> make(int r, int c) {
        List<Integer> co = new ArrayList<>();
        co.add(r);
        co.add(c);
        return co;
    }

    public static void main(String[] args) {
        System.out.println(new QueensAttackKing().queensAttacktheKing(ArrayUtils.read("[[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]]"), ArrayUtils.read1d("[3,3]")));
        System.out.println(new QueensAttackKing().queensAttacktheKing(ArrayUtils.read("[[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]]"), ArrayUtils.read1d("[3,4]")));
    }
}
