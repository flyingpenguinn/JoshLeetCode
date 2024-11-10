public class SmallestDivisibleDigitProductI {
    public int smallestNumber(int n, int t) {
        int cur = n;
        while (true) {
            String scur = String.valueOf(cur);
            int p = 1;
            for (int i = 0; i < scur.length(); ++i) {
                int cind = scur.charAt(i) - '0';
                p *= cind;
            }
            if (p % t == 0) {
                return cur;
            }
            ++cur;
        }
    }
}
