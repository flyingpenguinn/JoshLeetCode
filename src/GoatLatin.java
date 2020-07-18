public class GoatLatin {
    // trap is it could have caps letters too!
    public String toGoatLatin(String s) {
        // s non null len>=1
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder();
        StringBuilder as = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            as.append('a');
            StringBuilder converted = convert(ss[i], as);
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(converted);
        }
        return sb.toString();
    }

    private String vowels = "aeiouAEIOU";

    private StringBuilder convert(String s, StringBuilder as) {
        StringBuilder sb = new StringBuilder(s);
        char first = s.charAt(0);
        if (vowels.indexOf(first) != -1) {
            sb.append("ma");
        } else {
            sb.deleteCharAt(0);
            sb.append(first);
            sb.append("ma");
        }
        sb.append(as);
        return sb;
    }
}
