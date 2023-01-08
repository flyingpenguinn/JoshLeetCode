public class CategorizeBoxAccordingToCriteria {
    public String categorizeBox(int l, int w, int h, int m) {
        boolean isb = isb(l, w, h);
        boolean ish = m >= 100;
        if (isb && ish) {
            return "Both";
        } else if (isb) {
            return "Bulky";
        } else if (ish) {
            return "Heavy";
        } else {
            return "Neither";
        }
    }

    private boolean isb(long l, long w, long h) {
        long v1 = (long) 1e4;
        long v2 = (long) 1e9;
        if (l >= v1 || w >= v1 || h >= v1) {
            return true;
        }
        long vol = l * w * h;
        if (vol >= v2) {
            return true;
        }
        return false;
    }
}
