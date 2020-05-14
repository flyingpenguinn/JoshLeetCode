package base;

import java.util.Arrays;

public class Gcd {

    // linear combination of a and b. mit page 99
    int[] generalEuclid(int x, int y) {
        if (x < y) {
            int[] r = generalEuclid(y, x);
            return new int[]{r[0], r[2], r[1]};
        }
        return ggcd(x, y, 0, 1, 1, 0);
    }

    private int[] ggcd(int x, int y, int la, int lb, int lla, int llb) {
        if (y == 0) {
            return new int[]{x, lla, llb};
        }
        int q = x / y;
        int r = x % y;
        int na = lla - q * la;
        int nb = llb - q * lb;
        return ggcd(y, r, na, nb, la, lb);
    }

    boolean isprime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // a^b mod c
    int expmod(int a, int b, int c) {
        if (b == 1) {
            return a % c;
        }
        int half = expmod(a, b / 2, c);
        if (b % 2 == 0) {
            return (int) ((half * 1L * half) % c);
        } else {
            return (int) ((half * 1L * half * a) % c);
        }
    }

    int gcd(int x, int y) {
        return x < y ? gcd(y, x) : y == 0 ? x : gcd(y, x % y);
    }

    public static void main(String[] args) {
        System.out.println(new Gcd().expmod(34, 82248, 83));
        System.out.println(new Gcd().isprime(2347));
        System.out.println(new Gcd().gcd(7,3));
        System.out.println(new Gcd().gcd(12,3));
        System.out.println(new Gcd().gcd(3,3));
        System.out.println(Arrays.toString(new Gcd().generalEuclid(11, 72)));
        System.out.println(Arrays.toString(new Gcd().generalEuclid(21, 50)));
        System.out.println(Arrays.toString(new Gcd().generalEuclid(17, 13)));
        System.out.println(Arrays.toString(new Gcd().generalEuclid(259, 70)));
        System.out.println(Arrays.toString(new Gcd().generalEuclid(394268, 274268)));
        System.out.println(Arrays.toString(new Gcd().generalEuclid(21, 26)));

    }
}
