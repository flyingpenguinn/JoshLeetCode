public class ReplaceStringForBalance {
    public int balancedString(String s) {
        int[] all = new int[4];
        for (int i = 0; i < s.length(); i++) {
            all[getindex(s.charAt(i))]++;
        }

        int high = -1;
        int low = 0;
        int Max = 1000000;
        int min = Max;
        int n = s.length();
        int target = n / 4;
        int def = getDef(all, target);
        if (def == 0) {
            return 0;
        }
        int[] cur = new int[4];
        while (true) {
            int len = high - low + 1;
            if (notgood(cur, all, target)) {
                high++;
                if (high == n) {
                    break;
                }
                cur[getindex(s.charAt(high))]++;
            } else {
                min = Math.min(min, len);
                cur[getindex(s.charAt(low))]--;
                low++;
            }
        }
        return min >= Max ? -1 : min;
    }

    private boolean notgood(int[] cur, int[] all, int target) {
        for (int i = 0; i < all.length; i++) {
            int diff = all[i] - cur[i];
            if (diff > target) {
                return true;
            }
        }
        return false;
    }


    private int getDef(int[] count, int target) {
        int def = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] < target) {
                def += target - count[i];
            }
        }
        return def;
    }

    int getindex(char c) {
        if (c == 'Q') {
            return 0;
        }
        if (c == 'W') {
            return 1;
        }
        if (c == 'E') {
            return 2;

        } else {
            return 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ReplaceStringForBalance().balancedString("QWER"));
        System.out.println(new ReplaceStringForBalance().balancedString("QQWE"));
        System.out.println(new ReplaceStringForBalance().balancedString("QQQW"));
        System.out.println(new ReplaceStringForBalance().balancedString("QQQQ"));
        System.out.println(new ReplaceStringForBalance().balancedString("WWEQERQWQWWRWWERQWEQ"));

        System.out.println(new ReplaceStringForBalance().balancedString("WWEQERQWQWWRWWERQWEQ"));

    }
}
