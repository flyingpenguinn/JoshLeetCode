import java.util.*;

/*
LC#401
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryWatch {
    // put hour and min to the same array but can use range to differentiate them and validate
    int[] ts = {1, 2, 4, 8, 16, 32, 1, 2, 4, 8};

    public List<String> readBinaryWatch(int t) {
        List<String> r = new ArrayList<>();
        dol(0, t, 0, 0, r);
        return r;
    }

    void dol(int i, int t, int ch, int cm, List<String> r) {
        int n = ts.length;
        if (ch >= 12 || cm >= 60) {
            return;
        }
        if (t == 0) {
            String st = trans(ch, cm);
            if (!st.isEmpty()) {
                r.add(st);
            }
            return;
        }
        if (i == n) {
            return;
        }
        dol(i + 1, t, ch, cm, r);
        if (i < 6) {
            dol(i + 1, t - 1, ch, cm + ts[i], r);
        } else {
            dol(i + 1, t - 1, ch + ts[i], cm, r);
        }
    }

    String trans(int h, int m) {
        if (h >= 12) {
            return "";
        }
        if (m >= 60) {
            return "";
        }
        String nm = m < 10 ? "0" + m : "" + m;
        return h + ":" + nm;
    }

    public static void main(String[] args) {
        System.out.println(new BinaryWatch().readBinaryWatch(2));
    }
}
