import java.util.*;

public class SpecialBinaryString {

    // greedily look for the current best
    public String makeLargestSpecial(String s) {

        return doMake(s);

    }

    private String doMake(String s) {
        //  System.out.println(s);

        int n = s.length();
        String maxt = "";
        for (int i = 0; i < n; i++) {
            int c0 = 0;
            int c1 = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '0') {
                    c0++;
                } else {
                    c1++;
                }

                if (c1 == c0) {
                    // from j+1 take another special substring
                    int count0 = 0;
                    int count1 = 0;
                    for (int k = j + 1; k < n; k++) {
                        if (s.charAt(k) == '0') {
                            count0++;
                        } else {
                            count1++;
                        }
                        if (count0 == count1) {
                            String ns = swap(s, i, j, j + 1, k);
                            if (ns.compareTo(s) > 0) {
                                if (ns.compareTo(maxt) > 0) {
                                    maxt = ns;
                                }
                            }
                        } else if (count1 < count0) {
                            break;
                        }
                    }

                } else if (c1 < c0) {
                    // violated
                    break;
                }
            }
        }
        if (!maxt.isEmpty()) {
            return doMake(maxt);
        } else {
            return s;
        }
    }

    private String swap(String s, int i, int j, int k, int l) {
        return s.substring(0, i) + s.substring(k, l + 1) + s.substring(i, j + 1) + s.substring(l + 1);
    }

    public static void main(String[] args) {
        System.out.println(new SpecialBinaryString().makeLargestSpecial("110010111100001100101100101010101011011010100010"));
    }
}

// TODO do this myself
class SpecialBinaryStringAlternative {
    public String makeLargestSpecial(String S) {

        int balance = 0, l = 0;
        List<String> subResults = new LinkedList<>();
        for (int r = 0; r < S.length(); r++) {
            if (S.charAt(r) == '0') {
                balance--;
            } else {
                balance++;
            }
            if (balance == 0) {
                subResults.add("1" + makeLargestSpecial(S.substring(l + 1, r)) + "0");
                l = r + 1;
            }
        }
        Collections.sort(subResults, Collections.reverseOrder());

        return String.join("", subResults);
    }
}