public class MaxValueOfStringInArray {
    public int maximumValue(String[] strs) {
        int res = 0;
        for (String s : strs) {
            try {
                Integer num = Integer.valueOf(s);
                res = Math.max(res, num);
            } catch (NumberFormatException e) {

                res = Math.max(res, s.length());
            }
        }
        return res;
    }
}
