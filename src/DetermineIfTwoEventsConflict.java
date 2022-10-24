public class DetermineIfTwoEventsConflict {
    public boolean haveConflict(String[] a, String[] b) {
        int[] ta = new int[]{toint(a[0]), toint(a[1])};
        int[] tb = new int[]{toint(b[0]), toint(b[1])};
        if (ta[0] > tb[1] || tb[0] > ta[1]) {
            return false;
        }
        return true;
    }

    private int toint(String t) {
        int hour = Integer.valueOf(t.substring(0, 2));
        int mm = Integer.valueOf(t.substring(3, 5));
        return hour * 60 + mm;
    }
}
