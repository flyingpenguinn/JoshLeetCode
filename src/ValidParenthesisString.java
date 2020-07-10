import java.util.Arrays;

public class ValidParenthesisString {
    // max, min are the max or min POSSIBLE counts of left bracket
    // each * would +1 max, -1 min. we verify that max never goes below 0 and min is 0 in the end so that 0 is possible
    public boolean checkValidString(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        // only (, ), *
        int maxLeft = 0;
        int minLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                maxLeft++;
                minLeft++;
            } else if (c == '*') {
                maxLeft++; // could be left, or right. so in the "best" case we get left++, but in the "worst" we --
                minLeft--;
            } else {
                maxLeft--;
                minLeft--;
            }
            if (maxLeft < 0) {
                return false;
            }
            minLeft = Math.max(minLeft, 0);  // can't go below 0 at any time
        }
        return minLeft == 0;
    }
}

