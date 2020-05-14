public class RectangleOverlap {

    public boolean isRectangleOverlap(int[] r1, int[] r2) {
        boolean noOverlap = r2[2] <= r1[0] || r2[0] >= r1[2] || r2[1] >= r1[3] || r2[3] <= r1[1];
        return !noOverlap;
    }


    public static void main(String[] args) {

    }
}
