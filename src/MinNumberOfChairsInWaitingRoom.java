public class MinNumberOfChairsInWaitingRoom {
    public int minimumChairs(String s) {
        int n = s.length();
        int openchairs = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == 'E') {
                if (openchairs == 0) {
                    ++res;
                } else {
                    --openchairs;
                }
            } else {
                ++openchairs;
            }
            res = Math.max(res, openchairs);
        }
        return res;
    }
}
