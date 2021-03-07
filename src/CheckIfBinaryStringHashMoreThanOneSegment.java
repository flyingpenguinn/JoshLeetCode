public class CheckIfBinaryStringHashMoreThanOneSegment {
    public boolean checkOnesSegment(String s) {
        int i = 0;
        boolean found = false;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '0') {
                i++;
                continue;
            }
            if (found) {
                return false;
            }
            found = true;
            int j = i;
            while (j < s.length() && s.charAt(j) == '1') {
                j++;
            }
            i = j;
        }
        return true;
    }
}
