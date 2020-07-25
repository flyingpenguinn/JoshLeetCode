public class RectangleOverlap {

    // bottomleft and ropright can be got from min and max of bl and tr
    // then check if area is positive
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // check null and verify
        int[] topRight = new int[]{Math.min(rec1[2], rec2[2]), Math.min(rec1[3], rec2[3])};
        int[] bottomLeft = new int[]{Math.max(rec1[0], rec2[0]), Math.max(rec1[1], rec2[1])};
        if (bottomLeft[0] >= topRight[0] || bottomLeft[1] >= topRight[1]) {
            return false;
        } else {
            return true;
        }

    }


    public static void main(String[] args) {

    }
}
