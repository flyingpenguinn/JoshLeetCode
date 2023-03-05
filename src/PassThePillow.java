public class PassThePillow {
    public int passThePillow(int n, int time) {
        int cur = 1;
        int delta = 1;
        while (time > 0) {
            --time;
            cur += delta;
            if (cur == n) {
                delta = -1;
            } else if (cur == 1) {
                delta = 1;
            }
        }
        return cur;
    }
}
