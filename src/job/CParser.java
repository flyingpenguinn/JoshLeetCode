package job;

import java.util.List;

public class CParser {
    // assuming valid
    private String parse(String s) {
        int i = 0;
        int lenLen = 2;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            int lenEnd = i + lenLen;
            int len = Integer.valueOf(s.substring(i, lenEnd));
            int strEnd = lenEnd + len;
            String str = s.substring(lenEnd, strEnd);
            sb.append(str);
            i = strEnd;
        }
        return sb.toString();
    }

    private int toInt(char c) {
        return c - '0';
    }

    public String parseList(List<String> list) {
        // assuming valid, non null
        StringBuilder sb = new StringBuilder();
        int rowStart = 0;
        int i = 0;
        int pending = 0;
        while (i < list.size()) {
            int j = rowStart;
            rowStart = 0;
            String str = list.get(i);
            while (j < str.length() && pending > 0) {
                sb.append(str.charAt(j++));
                pending--;
            }
            while (j < str.length()) {
                if (j == str.length() - 1) {
                    pending = toInt(str.charAt(j)) * 10 + toInt(list.get(i + 1).charAt(0));
                    rowStart = 1;
                    break;
                } else {
                    pending = Integer.valueOf(str.substring(j, j + 2));
                    j += 2;
                    while (j < str.length() && pending > 0) {
                        sb.append(str.charAt(j++));
                        pending--;
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new CParser().parse("02bc10123456789003578"));// bc...0
        System.out.println(new CParser().parseList(List.of("0", "2bb01", "c")));// bbc
        System.out.println(new CParser().parseList(List.of("02bc101234567890")));// bc...0
        System.out.println(new CParser().parseList(List.of("02cc01b"))); //ccb

        System.out.println(new CParser().parseList(List.of("02", "bb01", "c")));//bbc
        System.out.println(new CParser().parseList(List.of("02c", "b03b", "bc")));//cbbbc
        System.out.println(new CParser().parseList(List.of("02b", "b02c", "c0", "1d")));//bbccd
    }
}
