import java.util.Arrays;

public class MaxXorWithElementFromArray {
    // combining the logic in LC#421 and LC#1697
    // in #1697 we sort query then run union find on it
    // in #421 we used a trie for max xor
    public int[] maximizeXor(int[] a, int[][] q) {
        int n = a.length;
        int qn = q.length;
        int[][] q2 = new int[qn][4];
        for (int i = 0; i < qn; i++) {
            q2[i][0] = q[i][0];
            q2[i][1] = q[i][1];
            q2[i][2] = i;
        }
        // limit small to big
        Arrays.sort(q2, (x, y) -> Integer.compare(x[1], y[1]));
        //   System.out.println(Arrays.deepToString(q2));
        Arrays.sort(a);
        int j = 0;
        for (int i = 0; i < qn; i++) {
            while (j < n && a[j] <= q2[i][1]) {
                insert(root, a[j]);
                j++;
            }
            int cur = -1;
            if (j > 0) {
                cur = find(root, q2[i][0]);
            }
//            System.out.println(q2[i][0]+" "+cur);
            q2[i][3] = cur;
        }
        int[] res = new int[qn];
        for (int i = 0; i < qn; i++) {
            int index = q2[i][2];
            res[index] = q2[i][3];
        }
        return res;
    }


    class Trie {
        private int val;
        private Trie[] ch = new Trie[2];

        public Trie(int val) {
            this.val = val;
        }
    }

    private Trie root = new Trie(-1);

    private void insert(Trie t, int num) {
        Trie p = root;
        for (int j = 31; j >= 0; j--) {
            int dig = ((num >> j) & 1);
            if (p.ch[dig] == null) {
                p.ch[dig] = new Trie(dig);
            }
            p = p.ch[dig];
        }
    }

    private int find(Trie t, int num) {
        int res = 0;
        Trie p = root;
        for (int j = 31; j >= 0; j--) {
            int dig = ((num >> j) & 1);
            int nd = dig ^ 1;
            if (p.ch[nd] != null) {
                p = p.ch[nd];
                res |= (1 << j);
            } else {
                p = p.ch[dig];
            }
        }
        return res;
    }
}
