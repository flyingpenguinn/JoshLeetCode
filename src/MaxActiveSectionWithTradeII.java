import java.util.ArrayList;
import java.util.List;

public class MaxActiveSectionWithTradeII {
    // TODO
    private static final int INF = (int) 1e9;
    private static final int MAXP = 20;

    // This helper finds the block index covering position x.
    // In C++ code, it was:
    //   upper_bound(vec.begin(), vec.end(), {x, INF}) - vec.begin() - 1;
    // We do a binary search for the largest i where vec[i].start <= x.
    private int findBlockIndexFor(List<int[]> vec, int x) {
        // Each block is [startPos, length].
        // We want the largest i with vec[i][0] <= x.
        int left = 0, right = vec.size();
        while (left < right) {
            int mid = (left + right) >>> 1;
            int startOfMid = vec.get(mid)[0];
            if (startOfMid <= x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1; // could be -1 if x < vec[0][0].
    }

    // Sparse Table RMQ query: returns max in [L..R].
    // If L>R, return -INF.
    private int queryRMQ(int[][] rmq, int L, int R) {
        if (L > R) return -INF;
        int length = R - L + 1;
        // largest power of 2 <= length
        int p = 31 - Integer.numberOfLeadingZeros(length);
        int span = 1 << p;
        return Math.max(rmq[p][L], rmq[p][R - span + 1]);
    }

    // getNum: how many '0's remain in the block idx after cutting by [l..r]?
    // If idx==bl (the left boundary block), we only keep from l..(blockEnd).
    // If idx==br (the right boundary block), we only keep from (blockStart)..r.
    // Otherwise the entire block remains.
    private int getNum(String s, List<int[]> vec, int idx, int l, int r, int bl, int br) {
        int[] block = vec.get(idx);
        int startPos = block[0];
        int lengthPos = block[1];
        // The block covers [startPos.. startPos+lengthPos-1]
        if (idx == bl) {
            // we cut from left up to (l-1)
            // how many remain from l..(startPos+lengthPos-1)?
            int remain = (startPos + lengthPos) - l;
            return remain;
        } else if (idx == br) {
            // we cut from right after r
            // how many remain from startPos..r?
            int remain = r - startPos + 1;
            return remain;
        } else {
            return lengthPos;
        }
    }

    // calc: if block idx is '0', return getNum(idx) + getNum(idx+2), else -INF.
    // This handles partial cuts on blocks idx (and idx+2).
    private int calc(String s, List<int[]> vec, int idx, int l, int r, int bl, int br) {
        if (idx < 0 || idx + 2 >= vec.size()) return -INF;
        // check if block idx is '0'
        int startOfBlock = vec.get(idx)[0];
        if (s.charAt(startOfBlock) == '1') {
            return -INF;
        }
        int sum = getNum(s, vec, idx, l, r, bl, br)
                + getNum(s, vec, idx + 2, l, r, bl, br);
        return sum;
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        // 1) Count total '1' in s
        int base = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') base++;
        }

        // 2) Compress s into consecutive blocks of identical bits
        //    Each block is [start, length]
        List<int[]> vec = new ArrayList<>();
        {
            int start = 0;
            for (int i = 0; i < n; i++) {
                // if boundary or s[i] != s[i+1], close off block
                if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                    vec.add(new int[]{start, i - start + 1});
                    start = i + 1;
                }
            }
        }
        int m = vec.size();

        // 3) Build Sparse Table rmq[p][i], storing the “score” for block i:
        //    if block i is '0' and (i+2 < m), rmq[0][i] = length(i)+length(i+2), else -INF.
        int[][] rmq = new int[MAXP][m];
        for (int i = 0; i < m; i++) {
            int startPos = vec.get(i)[0];
            char ch = s.charAt(startPos);
            if (ch == '1') {
                rmq[0][i] = -INF;
            } else {
                if (i + 2 < m) {
                    rmq[0][i] = vec.get(i)[1] + vec.get(i + 2)[1];
                } else {
                    rmq[0][i] = -INF;
                }
            }
        }
        // fill rmq[p][i] for p=1..MAXP-1
        for (int p = 1; p < MAXP; p++) {
            int len = 1 << p;
            int half = 1 << (p - 1);
            for (int i = 0; i + len - 1 < m; i++) {
                rmq[p][i] = Math.max(rmq[p - 1][i],
                        rmq[p - 1][i + half]);
            }
        }

        // 4) For each query, compute answer
        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) {
            int l = q[0], r = q[1];

            // find the block covering l, and the block covering r
            int bl = findBlockIndexFor(vec, l);
            int br = findBlockIndexFor(vec, r);

            // if (br - bl + 1 <= 2), not enough room to do a “remove '0' block i, remove '0' block i+2”
            // => answer = base
            if (br - bl + 1 <= 2) {
                ans.add(base);
                continue;
            }

            // best = max of:
            //   0,
            //   queryRMQ(rmq, bl+1, br-3),
            //   calc(bl), calc(br-2)
            int best = Math.max(queryRMQ(rmq, bl + 1, br - 3), 0);
            best = Math.max(best, calc(s, vec, bl, l, r, bl, br));
            best = Math.max(best, calc(s, vec, br - 2, l, r, bl, br));

            ans.add(base + best);
        }

        return ans;
    }
}
