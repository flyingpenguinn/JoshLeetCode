public class RectangleOverlap {

    // check if they are detached on the 4 directions!
    public boolean isRectangleOverlap(int[] a, int[] b) {
        return !(a[2] <= b[0] || a[0] >= b[2] || a[1] >= b[3] || a[3] <= b[1]);
    }


    public static void main(String[] args) {

    }
}
