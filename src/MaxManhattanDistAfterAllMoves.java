public class MaxManhattanDistAfterAllMoves {
    public int maxDistance(String moves) {
        int hori = 0;
        int verti = 0;
        int free = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'U') {
                ++verti;
            } else if (c == 'D') {
                --verti;
            } else if (c == 'L') {
                --hori;
            } else if (c == 'R') {
                ++hori;
            } else {
                ++free;
            }
        }
        int hv = Math.abs(hori);
        int vv = Math.abs(verti);
        return hv + vv + free;
    }
}
