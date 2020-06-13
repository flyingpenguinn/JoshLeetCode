import base.ArrayUtils;

import java.io.*;
import java.util.*;

/*
LC#421
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
 */
public class MaxXorOfNumbersInArray {
    // with trie we can check each digit for each string in On...
    // use the usual trie way: check if a string we want is in this trie! here we try to "make it big" by always going reverse
    class Trie {
        int v;

        public Trie(int v) {
            this.v = v;
        }

        Trie[] ch = new Trie[2];
    }

    Trie root = new Trie(-1);

    public int findMaximumXOR(int[] a) {
        for (int ai : a) {
            insert(root, ai, 31);
        }
        int max = 0;
        for (int ai : a) {
            int cur = max(root, ai, 31);
            max = Math.max(max, cur);
        }
        return max;
    }

    void insert(Trie n, int num, int i) {
        if (i < 0) {
            return;
        }
        int d = ((num >> i) & 1);
        Trie next = n.ch[d];
        if (next == null) {
            next = n.ch[d] = new Trie(d);
        }
        insert(next, num, i - 1);
    }

    int max(Trie n, int num, int i) {
        if (i < 0) {
            return 0;
        }
        int d = ((num >> i) & 1);
        int ed = d ^ 1;
        if (n.ch[ed] != null) {
            return (1 << i) + max(n.ch[ed], num, i - 1);
        } else {
            return max(n.ch[d], num, i - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\maxxor.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String str = reader.readLine();


        System.out.println(new MaxNumberOfXorAlternative().findMaximumXOR(ArrayUtils.read1d(str)));

    }
}

class MaxNumberOfXorAlternative {
    // alternative way of comparing two nodes...
    class Trie {
        int v;

        public Trie(int v) {
            this.v = v;
        }

        Trie[] ch = new Trie[2];
    }

    Trie root = new Trie(-1);

    public int findMaximumXOR(int[] a) {
        for (int ai : a) {
            insert(root, ai, 31);
        }
        int rt = domax(root, 31);
        return rt;
    }

    private int domax(Trie t, int i) {
        if (i == -1) {
            return 0;
        }
        if (t.ch[0] == null) {
            return domax(t.ch[1], i - 1);
        }
        if (t.ch[1] == null) {
            return domax(t.ch[0], i - 1);
        }

        int v1 = 0;
        int v2 = 0;
        if (t.ch[0] != null && t.ch[1] != null) {
            v1 = (1 << i) + domax(t.ch[0], t.ch[1], i - 1);
        }
        if (t.ch[1] != null && t.ch[0] != null) {
            v2 = (1 << i) + domax(t.ch[1], t.ch[0], i - 1);
        }
        return Math.max(v1, v2);
    }

    private int domax(Trie t1, Trie t2, int i) {
        if (i == -1) {
            return 0;
        }

        int v1 = 0;
        int v2 = 0;

        boolean countedone = false;
        if (t1.ch[0] != null && t2.ch[1] != null) {
            v1 = (1 << i) + domax(t1.ch[0], t2.ch[1], i - 1);
            countedone = true;
        }
        if (t2.ch[0] != null && t1.ch[1] != null) {
            v2 = (1 << i) + domax(t2.ch[0], t1.ch[1], i - 1);
            countedone = true;
        }
        if (countedone) {
            return Math.max(v1, v2);
        }
        int v3 = 0;
        int v4 = 0;
        if (t1.ch[0] != null && t2.ch[0] != null) {
            v3 = domax(t1.ch[0], t2.ch[0], i - 1);
        }
        if (t1.ch[1] != null && t2.ch[1] != null) {
            v4 = domax(t1.ch[1], t2.ch[1], i - 1);
        }
        return Math.max(v3, v4);
    }

    void insert(Trie n, int num, int i) {
        if (i < 0) {
            return;
        }
        int d = ((num >> i) & 1);
        Trie next = n.ch[d];
        if (next == null) {
            next = n.ch[d] = new Trie(d);
        }
        insert(next, num, i - 1);
    }


}