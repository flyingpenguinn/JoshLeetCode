public class CheckIfParenthesisStringCanBeValid {
    // just like #678 but * there can be empty. so we check if len%2==1
    public boolean canBeValid(String s, String locked) {
        StringBuilder ns = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (locked.charAt(i) == '0') {
                ns.append('*');
            } else {
                ns.append(s.charAt(i));
            }
        }
        return s.length() % 2 == 0 && checkValidString(ns.toString());
    }

    private boolean checkValidString(String s) {
        int minLeft = 0;
        int maxLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                minLeft--;
                maxLeft--;
            } else if (c == '(') {
                minLeft++;
                maxLeft++;
            } else {
                maxLeft++;
                minLeft--;
            }
            if (minLeft < 0) {
                minLeft = 0;
            }
            if (maxLeft < 0) {
                return false;
            }
        }
        return minLeft == 0;
    }
}
