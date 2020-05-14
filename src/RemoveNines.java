/*
LC#650
Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...

So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...

Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

Example 1:
Input: 9
Output: 10
Hint: n will not exceed 9 x 10^8.
 */
public class RemoveNines {
    // binary search baesd on how many numbers with 9 are <=n
    // we can also do one liner  return Integer.parseInt(Integer.toString(n, 9));  as these resutls are 9 based numbers
    public int newInteger(int n) {
        long l = 1;
        long u = Integer.MAX_VALUE;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long count = count(mid);
            if (count >= n) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    // 1.. n how many left if we exclude 9
    long nines(long n) {
        if (n < 9) {
            return 0;
        }
        long base = 1L;
        while (base <= n) {
            base *= 10L;
        }
        base /= 10;
        long nines = 0;
        while (n > 0) {
            long d = n / base;

            if (d == 9) {
                long overnine = n - base * 9 + 1;
                long below = nines(9 * base - 1);
                nines += below + overnine;
                break;
            } else {
                long basecount = dobase(base);
                nines += d * basecount;
            }
            n = n - base * d;
            base /= 10;
        }
        return nines;
    }

    long count(long n) {
        return n - nines(n);
    }

    long dobase(long base) {
        if (base == 1) {
            return 0;
        }
        long d10 = base / 10;
        return dobase(d10) * 9 + d10;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveNines().newInteger(900000000));
    }
}
