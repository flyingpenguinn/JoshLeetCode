import base.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubarrayWithXorAtleastK {
    class Trie {
        int v;
        int cnt = 0;
        Trie[] ch = new Trie[2];

        Trie(int v) {
            this.v = v;
        }
    }

    private final Trie root = new Trie(-1);
    private final int ALLBITS = 3;

    private void insert(long v) {
        Trie p = root;
        for (int j = ALLBITS; j >= 0; --j) {
            int d = (int) ((v >> j) & 1);
            if (p.ch[d] == null) {
                p.ch[d] = new Trie(d);
            }
            ++p.cnt;
            p = p.ch[d];
        }
        ++p.cnt;
    }

    public long countXorSubarrays(int[] a, int k) {
        int n = a.length;
        long pxor = 0;
        insert(0);
        long res = 0;
        for (int value : a) {
            pxor ^= value;
            Trie p = root;
            long cres = 0;
            for (int j = ALLBITS; j >= 0 && p != null; --j) {
                int kd = ((k >> j) & 1);
                int pd = (int) ((pxor >> j) & 1);
                int lookfor = pd ^ 1;
                if (kd == 0) {
                    if (p.ch[lookfor] != null) {
                        cres += p.ch[lookfor].cnt;
                    }
                    p = p.ch[pd];
                } else {
                    p = p.ch[lookfor];
                }
            }
            if (p != null) {
                cres += p.cnt;
            }
            insert(pxor);
            res += cres;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new SubarrayWithXorAtleastK().countXorSubarrays(ArrayUtils.read1d("16"), 1));
        System.out.println(new SubarrayWithXorAtleastK().countXorSubarrays(ArrayUtils.read1d("3,1,2,3"), 2));
    }
}
