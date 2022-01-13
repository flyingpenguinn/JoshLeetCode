public class PourWaterBetweenBucketsToMakeEqual {
    public double equalizeWater(int[] a, int loss) {
        double l = 0.0;
        double u = 1e5;
        double lossp = loss / 100.0;
        while (u - l > 1e-6) {
            double m = l + (u - l) / 2;
            double def = 0;
            double contri = 0;
            for (double ai : a) {
                if (ai < m) {
                    def += (m - ai);
                } else {
                    contri += (ai - m) * (1 - lossp);
                }
            }
            if (contri >= def) {
                l = m;
            } else {
                u = m;
            }
        }
        return l;
    }
}
