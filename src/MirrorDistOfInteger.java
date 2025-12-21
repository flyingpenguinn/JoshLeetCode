public class MirrorDistOfInteger {
    public int mirrorDistance(int n) {
        int revn = Integer.parseInt(new StringBuilder(String.valueOf(n)).reverse().toString());
        return Math.abs(n - revn);
    }
}
