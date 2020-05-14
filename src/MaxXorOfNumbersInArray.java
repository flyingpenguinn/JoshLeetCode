import base.ArrayUtils;

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
    // when we hit a node in trie we hit some string in it, a path in trie must belong to one string only
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

    public static void main(String[] args) {
        System.out.println(new MaxXorOfNumbersHashSet().findMaximumXOR(ArrayUtils.read1d("3,10,5,25,2,8")));
    }
}

class MaxXorOfNumbersHashSet {
    // use the fact that ^ is its own reverse
    public int findMaximumXOR(int[] a) {
        int max = 0;
        int n = a.length;
        for (int i = 31; i >= 0; i--) {
            max = max << 1;
            int target = max | 1;
            Set<Integer> pref = new HashSet<>();
            for (int j = 0; j < n; j++) {
                int moved = (a[j] >> i);
                pref.add(moved);
            }
            for (int j = 0; j < n; j++) {
                int moved = (a[j] >> i);
                int other = (target ^ moved);
                if (pref.contains(other)) {
                    max |= 1;
                    break;
                }
            }
        }
        return max;
    }
}
