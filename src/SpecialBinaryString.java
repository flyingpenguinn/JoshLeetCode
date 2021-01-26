import java.util.*;

public class SpecialBinaryString {
    // a special string must be 1xxxx0. and xxx must be the best special string too
    // this is similar to () problem trying to find the best () where ( is bigger
    // as long as we can swap neighboring ones, we can sort them

    public String makeLargestSpecial(String s) {
        if (s.isEmpty()) {
            return s;
        }
        List<String> ss = new ArrayList<>();
        int n = s.length();
        // if it can be split into ()()(), then we sort the outmost ones, and deal with inner biggest ones
        int i = 0;
        int start = i;
        int ones = 0;
        while (i < n) {
            if (s.charAt(i) == '1') {
                ones++;
            } else {
                ones--;
            }
            if (ones == 0) {
                // could be the whole string. in this case we take the wrapper out and deal with inner ones
                String inner = "1" + makeLargestSpecial(s.substring(start + 1, i)) + "0";
                ss.add(inner);
                start = i + 1;
            }
            i++;
        }

        Collections.sort(ss, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < ss.size(); k++) {
            sb.append(ss.get(k));
        }
        return sb.toString();
    }
}