public class EqualRationalNumber {
    public boolean isRationalEqual(String s, String t) {

        Fraction sf = toFraction(s);
        Fraction tf = toFraction(t);
        return sf.same(tf);

    }

    Fraction zero =
            new Fraction(0, 1);

    Fraction toFraction(String str) {
        Fraction f0 = zero;
        Fraction f1 = zero;
        Fraction f2 = zero;
        String[] sp = str.split("\\.");
        if (sp[0].length() > 0) {
            f0 = new Fraction(Integer.valueOf(sp[0]), 1);
        }
        if (sp.length == 1) {
            return f0;
        }
        String s = sp[1];
        int rpt = s.indexOf('(');
        rpt = rpt == -1 ? s.length() : rpt;
        String nrpt = s.substring(0, rpt);
        long f1b = (int) Math.pow(10, nrpt.length());
        if (!nrpt.isEmpty()) {
            long f1a = Integer.valueOf(nrpt);

            f1 = new Fraction(f1a, f1b);
        }
        if (rpt + 1 < s.length()) {
            String rpts = s.substring(rpt + 1, s.length() - 1);
            long f2a = Integer.valueOf(rpts);
            long f2b = (int) Math.pow(10, rpts.length()) - 1;

            f2b *= f1b;
            f2 = new Fraction(f2a, f2b);
        }
        Fraction ff = f1.add(f2);
        return f0.add(ff);
    }


    class Fraction {
        long a;
        long b;

        Fraction(long a, long b) {
            long gcd = gcd(a, b);
            this.a = a / gcd;
            this.b = b / gcd;
        }

        Fraction add(Fraction f2) {
            return new Fraction(a * f2.b + b * f2.a, b * f2.b);
        }

        long gcd(long a, long b) {
            if (a < b) {
                return gcd(b, a);
            }
            return b == 0L ? a : gcd(b, a % b);
        }

        boolean same(Fraction o) {
            if (a == 0 && o.a == 0) {
                return true;
            }
            return a == o.a && b == o.b;
        }
    }

    public static void main(String[] args) {
        System.out.println(new EqualRationalNumber().isRationalEqual("1414.1414(14)", "1414.14(1414)"));
    }
}
