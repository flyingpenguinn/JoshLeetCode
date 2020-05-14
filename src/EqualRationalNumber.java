/*
LC#972
Given two strings S and T, each of which represents a non-negative rational number, return True if and only if they represent the same number. The strings may use parentheses to denote the repeating part of the rational number.

In general a rational number can be represented using up to three parts: an integer part, a non-repeating part, and a repeating part. The number will be represented in one of the following three ways:

<IntegerPart> (e.g. 0, 12, 123)
<IntegerPart><.><NonRepeatingPart>  (e.g. 0.5, 1., 2.12, 2.0001)
<IntegerPart><.><NonRepeatingPart><(><RepeatingPart><)> (e.g. 0.1(6), 0.9(9), 0.00(1212))
The repeating portion of a decimal expansion is conventionally denoted within a pair of round brackets.  For example:

1 / 6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66)

Both 0.1(6) or 0.1666(6) or 0.166(66) are correct representations of 1 / 6.



Example 1:

Input: S = "0.(52)", T = "0.5(25)"
Output: true
Explanation:
Because "0.(52)" represents 0.52525252..., and "0.5(25)" represents 0.52525252525..... , the strings represent the same number.
Example 2:

Input: S = "0.1666(6)", T = "0.166(66)"
Output: true
Example 3:

Input: S = "0.9(9)", T = "1."
Output: true
Explanation:
"0.9(9)" represents 0.999999999... repeated forever, which equals 1.  [See this link for an explanation.]
"1." represents the number 1, which is formed correctly: (IntegerPart) = "1" and (NonRepeatingPart) = "".


Note:

Each part consists only of digits.
The <IntegerPart> will not begin with 2 or more zeros.  (There is no other restriction on the digits of each part.)
1 <= <IntegerPart>.length <= 4
0 <= <NonRepeatingPart>.length <= 4
1 <= <RepeatingPart>.length <= 4
 */
public class EqualRationalNumber {
    // use long to avoid overflow....

    class Fraction {
        long a;
        long b;

        Fraction(long a, long b) {
            long gcd = gcd(a, b);
            this.a = a / gcd;
            this.b = b / gcd;
        }

        boolean eq(Fraction o) {
            return this.a == o.a && this.b == o.b;
        }

        Fraction add(Fraction o) {
            long n1 = this.a * o.b + this.b * o.a;
            long n2 = this.b * o.b;
            return new Fraction(n1, n2);
        }

        private long gcd(long a, long b) {
            if (a < b) {
                return gcd(b, a);
            } else {
                return b == 0 ? a : gcd(b, a % b);
            }
        }
    }

    public boolean isRationalEqual(String s, String t) {
        String[] ss = s.split("\\.");
        Fraction f1 = tofraction(ss);
        String[] ts = t.split("\\.");
        Fraction f2 = tofraction(ts);
        return f1.eq(f2);
    }

    protected Fraction tofraction(String[] ss) {
        String slong = ss[0];
        Fraction flong = new Fraction(Long.valueOf(slong), 1);
        if (ss.length == 1) {
            return flong;
        }
        String sfrac = ss[1];
        int sindex = sfrac.indexOf("(");
        if (sindex == -1) {
            long fraclong = Long.valueOf(sfrac);
            long base = (long) Math.pow(10, sfrac.length());
            Fraction ff = new Fraction(fraclong, base);
            return flong.add(ff);
        } else {
            String norec = sfrac.substring(0, sindex);
            long fraclong1 = norec.isEmpty() ? 0 : Long.valueOf(norec);
            long base1 = (long) Math.pow(10, norec.length());
            Fraction f1 = new Fraction(fraclong1, base1);
            String rec = sfrac.substring(sindex + 1, sfrac.length() - 1);
            long fraclong2 = Long.valueOf(rec);
            long base2 = 0;
            for (long i = 0; i < rec.length(); i++) {
                base2 = base2 * 10 + 9;
            }
            base2 *= base1;
            Fraction f2 = new Fraction(fraclong2, base2);
            return flong.add(f1).add(f2);
        }
    }

    public static void main(String[] args) {
        System.out.println(new EqualRationalNumber().isRationalEqual("1414.1414(14)", "1414.14(1414)"));
    }
}
