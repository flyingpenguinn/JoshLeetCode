import java.util.TreeMap;

public class MinSubarrayXorWithBoundedRange {
    // use trie to find max subarray xor via prefix sum
    class Trie {
        int bit;

        public Trie(int b) {
            bit = b;
        }

        Trie[] ch = new Trie[2];
        int cnt = 0;
    }

    private Trie trie = new Trie(-1);

    private void insert(int v) {
        //  System.out.println("inserting "+v);
        Trie p = trie;
        for (int j = 15; j >= 0; --j) {
            int cd = (((v >> j) & 1));
            if (p.ch[cd] == null) {
                p.ch[cd] = new Trie(cd);
            }
            ++p.ch[cd].cnt;

            //    System.out.println("j="+j+" cd="+cd+" cnt="+p.ch[cd].cnt);
            p = p.ch[cd];
        }
    }

    private void remove(int v) {
        Trie p = trie;
        //  System.out.println("removing "+v);
        for (int j = 15; j >= 0; --j) {
            int cd = (((v >> j) & 1));
            //      System.out.println("j="+j+" cd="+cd+" cnt="+p.ch[cd].cnt);
            --p.ch[cd].cnt;
            if (p.ch[cd].cnt == 0) {

                //      System.out.println("removing at "+p+" cd="+cd);
                p.ch[cd] = null;
                break;
            }
            p = p.ch[cd];
        }
    }

    private int getmax(int v) {
        Trie p = trie;
        int res = 0;
        //   System.out.println("getting for "+v);
        for (int j = 15; j >= 0; --j) {
            int cd = (((v >> j) & 1));
            int ocd = cd ^ 1;
            //     System.out.println("j="+j);
            if (p.ch[ocd] == null) {
                p = p.ch[cd];
                //    System.out.println("nextp = "+p);
            } else {
                res |= (1 << j);
                p = p.ch[ocd];
            }
        }
        return res;
    }

    private void update(TreeMap<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int maxXor(int[] a, int target) {
        int n = a.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int j = 0;
        int[] next = new int[n];
        for (int i = 0; i < n; ++i) {
            while (j < n) {
                update(tm, a[j], 1);

                int diff = tm.lastKey() - tm.firstKey();
                //      System.out.println("i="+i+" j="+j+" tm="+tm+" diff="+diff+" "+(diff<=target));
                if (diff <= target) {
                    //      System.out.println("good adding j");
                    ++j;
                } else {
                    update(tm, a[j], -1);
                    break;
                }
            }
            next[i] = j;
            // up to j-1 is good
            //      System.out.println("i="+i+" j="+j);
            update(tm, a[i], -1);

        }
        // System.out.println(Arrays.toString(next));
        int[] xorsuf = new int[n + 1];
        xorsuf[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            xorsuf[i] = xorsuf[i + 1] ^ a[i];
        }
        j = 1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            while (j <= next[i]) {
                insert(xorsuf[j]);
                ++j;
            }
            int cv = xorsuf[i];
            int cur = getmax(cv);
            res = Math.max(res, cur);
            if (i > 0) {
                remove(xorsuf[i]);
            }
        }
        return res;
    }
}
