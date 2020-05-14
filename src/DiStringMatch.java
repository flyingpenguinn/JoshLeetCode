/*
LC#942
Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.

Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

If S[i] == "I", then A[i] < A[i+1]
If S[i] == "D", then A[i] > A[i+1]


Example 1:

Input: "IDID"
Output: [0,4,1,3,2]
Example 2:

Input: "III"
Output: [0,1,2,3]
Example 3:

Input: "DDI"
Output: [3,2,0,1]


Note:

1 <= S.length <= 10000
S only contains characters "I" or "D".
 */
public class DiStringMatch {
    // count is so that we know where to start. then d and i go from reverse direction
    // another way: We track high (h = N - 1) and low (l = 0) numbers within [0 ... N - 1]. When we encounter 'I', we insert the current low number and increase it.
    // With 'D', we insert the current high number and decrease it. In the end, h == l, so we insert that last number to complete the premutation.
    public int[] diStringMatch(String s) {
        int n = s.length();
        int is = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                is++;
            }
        }
        int start = n - is;
        int[] r = new int[n + 1];
        r[0] = start;
        int ni = start + 1;
        int nd = start - 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                r[i + 1] = ni++;
            } else {
                r[i + 1] = nd--;
            }
        }
        return r;
    }
}
