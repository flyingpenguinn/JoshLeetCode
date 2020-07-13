package crap;

import java.util.List;

public class CitadelParser {
    // assuming valid
    private String parse(String s) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            int count = Integer.valueOf(s.substring(i, i + 2));
            int j = i + 2;
            String str = s.substring(j, j + count);
            sb.append(str);
            i = j + count;
        }
        return sb.toString();
    }

    private int toInt(char c) {
        return c - '0';
    }

    private String parseList(List<String> list) {
        // assuming valid, non null
        StringBuilder sb = new StringBuilder();
        int nextStart = 0;
        int pending = 0;
        for (int k = 0; k < list.size(); k++) {
            String s = list.get(k);
            int i = nextStart;
            nextStart = 0; // dont forget to clear up!
            while (i < s.length() && pending > 0) {
                sb.append(s.charAt(i++));
                pending--;
            }
            while (i < s.length()) {
                if (i == s.length() - 1) {
                    pending = toInt(s.charAt(i)) * 10 + toInt(list.get(k + 1).charAt(0));
                    nextStart = 1;
                    break;
                } else {
                    pending = Integer.valueOf(s.substring(i, i + 2));
                    i += 2;
                    while (i < s.length() && pending > 0) {
                        sb.append(s.charAt(i++));
                        pending--;
                    }
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new CitadelParser().parseList(List.of("0", "2bb01", "c")));// bbc
        System.out.println(new CitadelParser().parseList(List.of("02bc101234567890")));// bc...0
        System.out.println(new CitadelParser().parseList(List.of("02cc01b"))); //ccb

        System.out.println(new CitadelParser().parseList(List.of("02", "bb01", "c")));//bbc
        System.out.println(new CitadelParser().parseList(List.of("02c", "b03b", "bc")));//cbbbc
        System.out.println(new CitadelParser().parseList(List.of("02b", "b02c", "c0", "1d")));//bbccd
    }
}
