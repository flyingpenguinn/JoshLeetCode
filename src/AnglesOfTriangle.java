import java.util.Arrays;

public class AnglesOfTriangle {
    public double[] internalAngles(int[] a) {
        Arrays.sort(a);
        if (a[0] + a[1] <= a[2]) {
            return new double[0];
        }
        double l1 = a[0];
        double l2 = a[1];
        double l3 = a[2];
        double[] angs = new double[3];
        angs[0] = Math.toDegrees(Math.acos((l2 * l2 + l3 * l3 - l1 * l1) / (2 * l2 * l3)));
        angs[1] = Math.toDegrees(Math.acos((l1 * l1 + l3 * l3 - l2 * l2) / (2 * l1 * l3)));
        angs[2] = 180.0 - angs[0] - angs[1];
        Arrays.sort(angs);
        return angs;
    }
}
