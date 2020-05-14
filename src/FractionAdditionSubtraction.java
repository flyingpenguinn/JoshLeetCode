public class FractionAdditionSubtraction {
    class Fraction {

        int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        int a;
        int b;

        public Fraction(String s) {
            String[] ss = s.split("/");
            int a = Integer.valueOf(ss[0]);
            int b = Integer.valueOf(ss[1]);
            process(a, b);
        }

        public Fraction(int a, int b) {
            process(a, b);
        }

        protected void process(int a, int b) {
            int gcd = gcd(a, b);
            this.a = a / gcd;
            this.b = b / gcd;
            if (this.a > 0 && this.b < 0) {
                this.a = -this.a;
                this.b = -this.b;
            }
        }

        Fraction plus(Fraction o) {
            return new Fraction(a * o.b + b * o.a, b * o.b);
        }

        Fraction minus(Fraction o) {
            return new Fraction(a * o.b - b * o.a, b * o.b);
        }

        public String toString() {
            return a + "/" + b;
        }
    }

    public String fractionAddition(String s) {
        Fraction cur = new Fraction(0, 1);
        StringBuilder sb = new StringBuilder();
        char op = '+';
        int sn = s.length();
        for (int i = 0; i <= sn; i++) {
            if (i == sn || s.charAt(i) == '+' || s.charAt(i) == '-') {
                String stub = sb.toString();
                if (!stub.isEmpty()) {
                    Fraction fs = new Fraction(stub);
                    cur = op == '+' ? cur.plus(fs) : cur.minus(fs);
                }
                if (i < sn) {
                    op = s.charAt(i);
                }
                sb = new StringBuilder();
            } else {
                sb.append(s.charAt(i));
            }
        }
        return cur.toString();
    }
}
