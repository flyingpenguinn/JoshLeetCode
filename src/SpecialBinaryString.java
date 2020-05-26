import java.util.*;

public class SpecialBinaryString {

    // get the best swap every time and recurse on this
    public String makeLargestSpecial(String s) {
        String max = s;
        int n = s.length();
        int i = 0;
        boolean found = false;
        while (i < n) {
            int ones = 0;
            int zeros = 0;
            int j = i;
            while (j < n) {
                if (s.charAt(j) == '1') {
                    ones++;
                } else {
                    zeros++;
                }
                if (ones < zeros) {
                    break;
                } else if (ones == zeros) {
                    // i...j
                    int kones = 0;
                    int kzeros = 0;
                    int k = j + 1;
                    while (k < n) {
                        if (s.charAt(k) == '1') {
                            kones++;
                        } else {
                            kzeros++;
                        }
                        if (kones < kzeros) {
                            break;
                        } else if (kones == kzeros) {
                            String swapped = swap(s, i, j, j + 1, k);
                            if (max.compareTo(swapped) < 0) {
                                max = swapped;
                                found = true;
                            }
                        }
                        k++;
                    }
                }
                j++;
            }
            i++;
        }
        return found ? makeLargestSpecial(max) : s;
    }

    private String swap(String s, int i, int j, int k, int l) {
        return s.substring(0, i) + s.substring(k, l + 1) + s.substring(i, j + 1) + s.substring(l + 1);
    }

    public static void main(String[] args) {
        System.out.println(new SpecialBinaryStringQuicker().makeLargestSpecial("11011000"));
    }
}

class SpecialBinaryStringQuicker {
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