import base.ArrayUtils;

import java.util.*;

/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 */
public class CountOfSmallerNumberAfterSelf {

    // use BIT the same way as CountOfRangeSum.
    // use rank map and note we need to copy array when computing it
    public List<Integer> countSmaller(int[] a) {
        Map<Integer, Integer> rank = new HashMap<>();
        int n = a.length;
        List<Integer> r = new ArrayList<>();
        if (n == 0) {
            return r;
        }
        int[] ca = Arrays.copyOf(a, n);
        // must take a copy
        Arrays.sort(ca);
        rank.put(ca[0], 1);
        int pr = 2;
        for (int i = 1; i < n; i++) {
            if (ca[i] != ca[i - 1]) {
                rank.put(ca[i], pr++);
            }
        }

        int[] b = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int cr = rank.get(a[i]);


            int ri = p(b, cr - 1); //<
            r.add(ri);
            u(b, cr);
        }

        Collections.reverse(r);
        return r;
    }

    int p(int[] b, int i) {
        int r = 0;
        while (i > 0) {
            r += b[i];
            i -= i & (-i);
        }
        return r;
    }

    void u(int[] b, int i) {
        while (i < b.length) {
            b[i]++;
            i += i & (-i);
        }
    }

    public static void main(String[] args) {
        System.out.println(new CountOfSmallerAfterSelfMergesort().countSmaller(ArrayUtils.read1d("[2,0,1]")));
//        System.out.println(new CountOfSmallerAfterSelfMergesort().countSmaller(ArrayUtils.read1d("[5,2,6,1]")));
    }
}

class CountOfSmallerAfterSelfBst {

    class BSTnode {
        int val;
        BSTnode left;
        BSTnode right;
        // need to separate the two
        int count;
        int selfcount;

        public BSTnode(int val) {
            this.val = val;
            this.count = 1;
            this.selfcount = 1;
        }
    }

    BSTnode root = null;

    // do check and insertion in one shot
    // how many are < num in the subtree of node, before this insertion
    private int insert(BSTnode node, int num) {
        if (root == null) {
            root = new BSTnode(num);
            return 0;
        }
        if (node.val == num) {
            node.count++;
            node.selfcount++;
            return getLeftCount(node);
        } else if (node.val < num) {
            node.count++;
            if (node.right == null) {
                node.right = new BSTnode(num);
                return getLeftCount(node) + node.selfcount;
            } else {
                int rightcount = insert(node.right, num);
                return getLeftCount(node) + node.selfcount + rightcount;
            }
        } else {
            node.count++;
            if (node.left == null) {
                node.left = new BSTnode(num);
                return 0;
            } else {
                return insert(node.left, num);
            }
        }
    }

    private int getLeftCount(BSTnode node) {
        return node.left == null ? 0 : node.left.count;
    }


    public List<Integer> countSmaller(int[] nums) {
        LinkedList<Integer> r = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int number = nums[i];
            int count = insert(root, number);
            r.offerFirst(count);
        }
        return r;
    }
}

class CountOfSmallerAfterSelfMergesort {
    // similar to reverse pair, with a value to index mapping
    class ValueIndex {
        int v;
        int index;

        public ValueIndex(int v, int index) {
            this.v = v;
            this.index = index;
        }
    }

    int[] r;

    public List<Integer> countSmaller(int[] a) {
        int n = a.length;
        r = new int[n];
        ValueIndex[] vi = new ValueIndex[n];
        // create mapping in this way so that we know the original index
        for (int i = 0; i < n; i++) {
            vi[i] = new ValueIndex(a[i], i);
        }
        doc(vi, 0, n - 1);
        List<Integer> rr = new ArrayList<>();
        for (int i : r) {
            rr.add(i);
        }
        return rr;
    }

    void doc(ValueIndex[] a, int l, int u) {
        if (l >= u) {
            return;
        }
        ValueIndex[] t = new ValueIndex[u - l + 1];
        int m = l + (u - l) / 2;
        doc(a, l, m);
        doc(a, m + 1, u);
        int i = l;
        int j = m + 1;
        int ti = 0;
        int sc = 0;
        while (i <= m && j <= u) {
            if (a[i].v <= a[j].v) {
                t[ti++] = a[i];
                r[a[i].index] += sc;
                i++;
            } else {
                t[ti++] = a[j];
                sc++;
                j++;
            }
        }
        while (i <= m) {
            t[ti++] = a[i];
            r[a[i].index] += sc;
            i++;
        }
        while (j <= u) {
            t[ti++] = a[j];
            j++;
        }
        for (int k = l; k <= u; k++) {
            a[k] = t[k - l];
        }
    }
}
