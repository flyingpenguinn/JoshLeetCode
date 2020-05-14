import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidSquare {
    // 1234, 1243, 1324, 1342, 1423, 1432
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return issquare(p1, p2, p3, p4) || issquare(p1, p2, p4, p3) || issquare(p1, p3, p2, p4) || issquare(p1, p3, p4, p2) || issquare(p1, p4, p2, p3) || issquare(p1, p4, p3, p2);
    }

    boolean issquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int d1 = dist(p1, p2);
        int d2 = dist(p2, p3);
        int d3 = dist(p3, p4);
        int d4 = dist(p4, p1);
        if (d1 == d2 && d2 == d3 && d3 == d4 && d1 != 0) {
            // !=0 due to edge >0
            if (rightangle(p1, p2, p3, p4)) {
                // only need to check one right angle
                return true;
            }
        }
        return false;
    }

    int dist(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return dx * dx + dy * dy;
    }

    boolean rightangle(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] v1 = new int[]{p2[0] - p1[0], p2[1] - p1[1]};
        int[] v2 = new int[]{p3[0] - p2[0], p3[1] - p2[1]};
        return v1[0] * v2[0] + v1[1] * v2[1] == 0;
    }


    public static void main(String[] args) {
        int[] p1 = {0, 0};
        int[] p2 = {0, 1};
        int[] p3 = {1, 0};
        int[] p4 = {1, 1};
        System.out.println(new ValidSquare().validSquare(p1, p2, p3, p4));
    }
}
