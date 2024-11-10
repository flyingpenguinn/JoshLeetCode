import java.util.ArrayList;
import java.util.List;

public class SmallestDivisibleDigitProductII {
    public String smallestNumber(String num, long val) {
        List<Integer> vec = get(val);
        if (vec.size() == 0) return "-1";

        List<Integer> prefix = new ArrayList<>(List.of(0, 0, 0, 0));
        for (int i = 0; i < num.length(); i++) {
            List<Integer> temp = get(num.charAt(i) - '0');
            add(prefix, temp);
        }

        int zeroIndex = -1;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '0') {
                zeroIndex = i;
                break;
            }
        }

        if (ok(prefix, vec) && zeroIndex == -1) {
            return num;
        }

        int postlen = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            List<Integer> temp = get(d);
            remove(prefix, temp);
            if (zeroIndex != -1 && i > zeroIndex) {
                postlen++;
                continue;
            }

            for (int j = d + 1; j <= 9; j++) {
                temp = get(j);
                add(prefix, temp);
                remove(vec, prefix); // how much needed

                List<Integer> t = construct(vec);
                int cnt = 0;
                for (int k = 2; k < 10; k++) cnt += t.get(k);

                if (cnt <= postlen) {
                    List<Integer> tempPrefix = add1(prefix, t);
                    if (ok(tempPrefix, vec)) {
                        StringBuilder ans = new StringBuilder(num.substring(0, i));
                        ans.append((char) (j + '0'));
                        while (cnt != postlen) { // fill empty with 1
                            cnt++;
                            ans.append('1');
                        }
                        for (int k = 1; k < 10; k++) {
                            while (t.get(k) > 0) {
                                ans.append((char) (k + '0'));
                                t.set(k, t.get(k) - 1);
                            }
                        }
                        return ans.toString();
                    }
                }

                add(vec, prefix);
                remove(prefix, temp);
            }
            postlen++;
        }

        StringBuilder ans = new StringBuilder();
        List<Integer> t = construct(vec);
        for (int i = 1; i < 10; i++) {
            while (t.get(i) > 0) {
                ans.append((char) (i + '0'));
                t.set(i, t.get(i) - 1);
            }
        }
        StringBuilder one = new StringBuilder();
        while (one.length() + ans.length() <= num.length()) one.append("1");

        return one.append(ans).toString();
    }

    private List<Integer> get(long n) {
        List<Integer> vec = new ArrayList<>(List.of(0, 0, 0, 0));
        if (n == 0) return vec;
        while (n % 2 == 0) { n /= 2; vec.set(0, vec.get(0) + 1); }
        while (n % 3 == 0) { n /= 3; vec.set(1, vec.get(1) + 1); }
        while (n % 5 == 0) { n /= 5; vec.set(2, vec.get(2) + 1); }
        while (n % 7 == 0) { n /= 7; vec.set(3, vec.get(3) + 1); }
        if (n != 1) return new ArrayList<>();
        return vec;
    }

    private List<Integer> construct(List<Integer> vec) {
        List<Integer> ans = new ArrayList<>(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        if (vec.get(2) > 0) ans.set(5, vec.get(2)); // for 5
        if (vec.get(3) > 0) ans.set(7, vec.get(3)); // for 7
        int two = Math.max(vec.get(0), 0);
        int three = Math.max(vec.get(1), 0);

        while (two >= 3) { two -= 3; ans.set(8, ans.get(8) + 1); }
        while (three >= 2) { three -= 2; ans.set(9, ans.get(9) + 1); }

        if (two == 1 && three == 0) {
            ans.set(2, ans.get(2) + 1);
        } else if (two == 2 && three == 1) {
            ans.set(2, ans.get(2) + 1);
            ans.set(6, ans.get(6) + 1);
        } else if (two == 1 && three == 1) {
            ans.set(6, ans.get(6) + 1);
        } else if (two == 2 && three == 0) {
            ans.set(4, ans.get(4) + 1);
        } else if (two == 0 && three == 1) {
            ans.set(3, ans.get(3) + 1);
        }
        return ans;
    }

    private boolean ok(List<Integer> vec1, List<Integer> vec2) {
        for (int i = 0; i < 4; i++) {
            if (vec1.get(i) < vec2.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void add(List<Integer> vec1, List<Integer> vec2) {
        for (int i = 0; i < 4; i++) {
            vec1.set(i, vec1.get(i) + vec2.get(i));
        }
    }

    private void remove(List<Integer> vec1, List<Integer> vec2) {
        for (int i = 0; i < 4; i++) {
            vec1.set(i, vec1.get(i) - vec2.get(i));
        }
    }

    private List<Integer> add1(List<Integer> vec1, List<Integer> vec10) {
        List<Integer> temp = new ArrayList<>(vec1);
        temp.set(0, temp.get(0) + vec10.get(2) + vec10.get(6) + 2 * vec10.get(4) + 3 * vec10.get(8));
        temp.set(1, temp.get(1) + vec10.get(3) + vec10.get(6) + 2 * vec10.get(9));
        temp.set(2, temp.get(2) + vec10.get(5));
        temp.set(3, temp.get(3) + vec10.get(7));
        return temp;
    }
}
