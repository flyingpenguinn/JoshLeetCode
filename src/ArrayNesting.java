/*
LC#565
A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.

Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.



Example 1:

Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation:
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}


Note:

N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of A is an integer within the range [0, N-1].
 */
public class ArrayNesting {

    public int arrayNesting(int[] a) {
        int n = a.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] >= 0) {
                int l = dfs(a, i);
                max = Math.max(l, max);
            }
        }
        return max;
    }

    // we don't have 2 nodes pointing to the same node. so all circles are full
    int dfs(int[] a, int i) {

        int oai = a[i];
        a[i] = -1;
        if (a[oai] == -1) {
            return 1;
        }

        // all distinct, no fwd or cross edge
        return 1 + dfs(a, oai);
    }
}

class ArrayNestingSwap {

    public int arrayNesting(int[] a) {

        int max = 0;
        for (int i = 0; i < a.length; i++) {
            int t = 0;
            while (a[i] != i) {
                swap(a, i, a[i]);
                t++;
            }
            max = Math.max(max, t + 1);
        }
        return max;
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
