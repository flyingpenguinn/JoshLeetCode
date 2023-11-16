interface BigArray {

    public int at(long index);

    public long size();
}

public class NumberOfEqualNumberBlocks {
    public int countBlocks(BigArray a) {
        long size = a.size();
        return solve(a, 0L, size);
    }

    private int solve(BigArray a, long i, long n) {
        if (i == n) {
            return 0;
        }
        int t = a.at(i);
        long pos = binary(a, i, n, t);
        return 1 + solve(a, pos + 1, n);
    }

    private long binary(BigArray a, long i, long n, int t) {
        long l = i;
        long u = n - 1;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (a.at(mid) == t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
