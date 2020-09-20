public class RearrangeSpacesBetweenWords {
    public String reorderSpaces(String text) {
        int space = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                space++;
            }
        }
        String[] ss = text.trim().split("\\s+");
        // System.out.println(Arrays.toString(ss));
        if (ss.length == 1) {
            return ss[0] + pad(space);
        }
        int sp = space / (ss.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            sb.append(ss[i]);
            if (i < ss.length - 1) {
                sb.append(pad(sp));
            }
        }
        sb.append(pad(space % (ss.length - 1)));
        return sb.toString();
    }

    private String pad(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(" ");
            n--;
        }
        return sb.toString();
    }
}
