public class KthSmallestPrimeFraction {
    class Fraction implements Comparable<Fraction> {
        int a;
        int b;

        public Fraction(int a, int b) {
            this.a = a;
            this.b = b;
        }

        Fraction add(Fraction o) {
            return new Fraction(this.a * o.b + this.b * o.a, this.b * o.b);
        }

        Fraction divide(int num) {
            if (this.a % num == 0) {
                return new Fraction(a / num, b);
            } else {
                return new Fraction(a, b * num);
            }
        }

        @Override
        public int compareTo(Fraction o) {
            return Integer.compare(this.a * o.b, this.b * o.a);
        }
    }

    public int[] kthSmallestPrimeFraction(int[] a, int k) {
        int n = a.length;
        Fraction l = new Fraction(1, a[n - 1]);
        Fraction smallest = l;
        Fraction u = new Fraction(a[n - 1], 1);
        while (l.compareTo(u) <= 0) {
            Fraction mid = l.add(u).divide(2);
            if (count(mid, a) <= k) {
                l = mid.add(smallest);
            } else {
                u = mid.add(smallest);
            }
        }
        int[] r = new int[2];
        r[0] = u.a;
        r[1] = u.b;
        return r;
    }

    private int count(Fraction mid, int[] a) {
        int n = a.length;
        int i = 0;
        int j = 0;
        int count = 0;
        while (j < n) {

            if (i < n && new Fraction(a[i], a[j]).compareTo(mid) >= 0) {
                i++;
            } else {
                count += i;
                j++;
            }
        }
        return count + 1;
    }
}
