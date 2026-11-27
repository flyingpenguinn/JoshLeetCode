package base;

import java.util.Objects;

// Reusable reduced fraction: denominator normalized to > 0; every x/0 becomes 1/0 (acts as +infinity in compareTo).
// Good as a HashMap key for slopes (149). Usage: new Fractions.Fraction(dy, dx).
public class Fractions {
    public static class Fraction implements Comparable<Fraction> {
        public final long a; // numerator
        public final long b; // denominator, always > 0 (or 0 for ±1/0)

        public Fraction(long a, long b) {
            if (a == 0 && b == 0) {
                throw new ArithmeticException("0/0");
            }
            if (b < 0 || (b == 0 && a < 0)) {
                a = -a;
                b = -b;
            }
            long g = gcd(Math.abs(a), b);
            this.a = a / g;
            this.b = b / g;
        }

        private static long gcd(long x, long y) {
            return y == 0 ? x : gcd(y, x % y);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Fraction f)) return false;
            return a == f.a && b == f.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        @Override
        public String toString() {
            return a + "/" + b;
        }

        // assumes |a|, |b| <= ~3e9 so the cross products fit in long
        @Override
        public int compareTo(Fraction o) {
            return Long.compare(a * o.b, o.a * b);
        }
    }
}
