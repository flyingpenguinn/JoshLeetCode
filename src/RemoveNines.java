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
    public int newInteger(int n) {
        long l = 1;
        long u = Integer.MAX_VALUE;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (count(mid) >= n) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    private long count(long n) {
        return n - getnines(n);
    }

    // how many numbers with 9 <=n
    private long getnines(long n) {
        if (n < 9) {
            return 0;
        }
        // how many non 9 numbers <=n
        long base = 1;
        while (base <= n) {
            base *= 10;
        }
        base /= 10;
        // conver to 3456 vs base = 1000
        int r = 0;
        // 3456 => 3*1000=> 3*1000's count
        r += (n / base) * countbase(base);
        if (n / base == 9) {
            r += (n - n / base * base + 1);
        } else {
            r += getnines(n - n / base * base);
        }
        return r;
    }

    // 100 has 19 nines, == 9*count(10)+10
    private long countbase(long n) {
        if (n == 1) {
            return 0;
        }
        return 9 * countbase(n / 10) + n / 10;
    }


    public static void main(String[] args) {
        System.out.println(new RemoveNinesQuick().newInteger(2000000));
        System.out.println(new RemoveNines().newInteger(2000000));
    }
}


class RemoveNinesQuick {
    // just convert n to base 9...
    public int newInteger(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int mod = n % 9;
            sb.append(mod);
            n /= 9;
        }
        return Integer.valueOf(sb.reverse().toString());
    }
}