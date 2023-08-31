public class FurthestDistanceFromOrigin {
    public int furthestDistanceFromOrigin(String s) {
        int n = s.length();
        int dist = 0;
        int buffer = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == 'L') {
                --dist;
            } else if (c == 'R') {
                ++dist;
            } else {
                ++buffer;
            }
        }
        return Math.abs(dist) + buffer;
    }
}
