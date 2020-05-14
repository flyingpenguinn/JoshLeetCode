public class GoatLatin {
    String vow = "aeiouAEIOU"; // caps too

    public String toGoatLatin(String str) {
        StringBuilder sb = new StringBuilder();
        StringBuilder as = new StringBuilder("a");

        String[] ss = str.split(" ");
        for (String s : ss) {
            if (s.isEmpty()) {
                continue;
            }
            char head = s.charAt(0);
            String cur = null;
            if (vow.indexOf(head) != -1) {
                cur = s + "ma" + as.toString();
            } else {
                cur = s.substring(1) + head + "ma" + as.toString();
            }
            String sp = sb.length() == 0 ? "" : " ";
            sb.append(sp);
            sb.append(cur);
            as.append("a");
        }
        return sb.toString();

    }
}
