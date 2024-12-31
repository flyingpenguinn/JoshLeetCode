public class FindLexILargestStringFromBoxI {
    public String answerString(String s, int t) {
        if (t == 1) {
            return s;
        }
        int n = s.length();
        int len = n - t + 1;
        String res = null;
        for (int i = 0; i < n; ++i) {
            int end = Math.min(n, i + len);
            String sub = s.substring(i, end);
            //System.out.println("i="+i+" sub="+sub);
            if (res == null || sub.compareTo(res) > 0) {
                res = sub;
            }
        }
        return res;
    }
}
