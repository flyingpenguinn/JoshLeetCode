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
    // for each num, the max we can get is to negate each bit of it. check if it's possible.
    // if it's possible to negate at high bit, do it otherwise take the same bit. we always have some bits to take
    // because for each num we populate the whole 32 bits
    class Trie {
        private int val;
        private Trie[] ch = new Trie[2];

        public Trie(int val) {
            this.val = val;
        }
    }

    private Trie root = new Trie(-1);

    public int findMaximumXOR(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            add(a[i]);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            int cur = maxXor(a[i]);
            max = Math.max(max, cur);
        }
        return max;
    }

    private void add(int num) {
        Trie p = root;
        // from high to low, hence from 31 to 0, not 0 to 31
        for (int i = 31; i >= 0; i--) {
            int bit = ((num >> i) & 1);
            if (p.ch[bit] == null) {
                p.ch[bit] = new Trie(bit);
            }
            p = p.ch[bit];
        }
    }

    private int maxXor(int num) {
        Trie p = root;
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = ((num >> i) & 1);
            int nbit = bit ^ 1;
            if (p.ch[nbit] != null) {
                p = p.ch[nbit];
                res |= (1 << i);
            } else {
                p = p.ch[bit];
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\maxxor.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String str = reader.readLine();


        System.out.println(new MaxXorOfNumbersInArray().findMaximumXOR(ArrayUtils.read1d(str)));

    }
}
