public class ShortestDistanceToChar {

    // split the dependency on both sides!
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] r = new int[n];
        int la = -n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                r[i] = 0;
                la = i;
            } else {
                r[i] = i - la;
            }
        }
        la = 2 * n + 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                la = i;
            } else {
                r[i] = Math.min(r[i], la - i);
            }
        }
        return r;
    }
}

// also 2*n but more circuitous
class ShortestDistanceToCharAltenative {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] r = new int[n];
        int posl = s.indexOf(c);

        for (int i = posl; i >= 0; i--) {
            r[i] = posl - i;
        }
        int la = posl;
        for (int i = posl + 1; i < n; i++) {
            if (s.charAt(i) == c) {
                r[i] = 0;
                la = i;
            } else {
                r[i] = i - la;
            }
        }
        int posr = s.lastIndexOf(c);
        if (posr == posl) {
            return r;
        }
        la = posr;
        for (int i = posr - 1; i > posl; i--) {
            if (s.charAt(i) == c) {
                la = i;
            } else {
                r[i] = Math.min(r[i], la - i);
            }
        }
        return r;
    }
}
