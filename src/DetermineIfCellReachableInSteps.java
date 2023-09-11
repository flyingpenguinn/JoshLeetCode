public class DetermineIfCellReachableInSteps {
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        int dx = Math.abs(fx - sx);
        int dy = Math.abs(fy - sy);
        int minsteps = Math.max(dx, dy);
        if (t < minsteps) {
            return false;
        }
        if (minsteps == 0 && t == 1) {
            return false;
        }
        return true;

    }
}
