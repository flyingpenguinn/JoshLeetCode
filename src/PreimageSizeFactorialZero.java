/*
LC#793
Let f(x) be the number of zeroes at the end of x!. (Recall that x! = 1 * 2 * 3 * ... * x, and by convention, 0! = 1.)

For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has 2 zeroes at the end. Given K, find how many non-negative integers x have the property that f(x) = K.

Example 1:
Input: K = 0
Output: 5
Explanation: 0!, 1!, 2!, 3!, and 4! end with K = 0 zeroes.

Example 2:
Input: K = 5
Output: 0
Explanation: There is no x such that x! ends in K = 5 zeroes.
Note:

K will be an integer in the range [0, 10^9].
 */
public class PreimageSizeFactorialZero {
    // naive way of counting...
    public int preimageSizeFZF(int k) {
        long first = find(k, true);
        long last = find(k, false);
        return (int) (last - first + 1);
    }

    private long find(int k, boolean isfirst) {
        long l = 0;
        long u = Long.MAX_VALUE;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long cnd = countzero(mid);
            if (cnd < k) {
                l = mid + 1;
            } else if (cnd > k) {
                u = mid - 1;
            } else {
                if (isfirst) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return isfirst ? l : u; // == k, or the first that is </> it
    }

    private long countzero(long n) {
        long r = 0;
        for (long base = 5; base <= n; base *= 5) {
            r += n / base;
        }
        return r;
    }
}

class PreImageSizeOfFactorialTrailingZeroBetter {
    // if we found, it must be 5...because in 5 steps we will get another 5 and get another 0
    public int preimageSizeFZF(int k) {
        long rt = find(k, true);
        return rt == -1 ? 0 : 5;
    }

    private long find(int k, boolean isfirst) {
        long l = 0;
        long u = Long.MAX_VALUE;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long cnd = countzero(mid);
            if (cnd < k) {
                l = mid + 1;
            } else if (cnd > k) {
                u = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private long countzero(long n) {
        long r = 0;
        for (long base = 5; base <= n; base *= 5) {
            r += n / base;
        }
        return r;
    }
}