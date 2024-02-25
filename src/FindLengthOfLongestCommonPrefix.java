import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindLengthOfLongestCommonPrefix {
    class Trie {
        int v;
        Trie[] ch = new Trie[10];

        public Trie(int v) {
            this.v = v;
        }
    }

    public int longestCommonPrefix(int[] as, int[] bs) {
        if (as.length > bs.length) {
            return solve(as, bs);
        } else {
            return solve(bs, as);
        }
    }

    private Trie root = new Trie(-1);

    private void insert(int v) {
        List<Integer> list = new ArrayList<>();
        while (v > 0) {
            int c = v % 10;
            list.add(c);
            v /= 10;
        }
        Collections.reverse(list);
        Trie p = root;
        for (int i = 0; i < list.size(); ++i) {
            int ci = list.get(i);
            //  System.out.println("inserting "+ci+" at "+p);
            if (p.ch[ci] == null) {
                p.ch[ci] = new Trie(ci);
            }
            p = p.ch[ci];
        }
    }

    private int find(int v) {
        List<Integer> list = new ArrayList<>();
        while (v > 0) {
            int c = v % 10;
            list.add(c);
            v /= 10;
        }
        Collections.reverse(list);
        int i = 0;
        Trie p = root;
        for (; i < list.size(); ++i) {
            int ci = list.get(i);
            //   System.out.println("checking "+ci+" at "+p);
            if (p.ch[ci] == null) {
                //      System.out.println("not found, break");
                break;
            }
            p = p.ch[ci];
        }
        return i;
    }

    // as longer, convert to trie
    private int solve(int[] as, int[] bs) {
        for (int ai : as) {
            insert(ai);
        }
        int res = 0;
        for (int bi : bs) {
            int len = find(bi);
            res = Math.max(res, len);
        }
        return res;
    }
}
