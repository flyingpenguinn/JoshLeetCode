public class GenerateTagForVideoCaption {
    private String firstUpper(String s) {
        if (s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(s.charAt(0)));
        int n = s.length();
        for (int i = 1; i < n; ++i) {
            sb.append(Character.toLowerCase(s.charAt(i)));
        }
        return sb.toString();
    }

    private String firstLow(String s) {
        if (s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(s.charAt(0)));
        int n = s.length();
        for (int i = 1; i < n; ++i) {
            sb.append(Character.toLowerCase(s.charAt(i)));
        }
        return sb.toString();
    }

    public String generateTag(String s) {
        s = s.strip();
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        String[] ss = s.split(" ");
        int sn = ss.length;
        sb.append(firstLow(ss[0]));
        for (int i = 1; i < sn; ++i) {
            sb.append(firstUpper(ss[i]));
        }
        sn = sb.length();
        StringBuilder nsb = new StringBuilder();
        for (int i = 0; i < sn; ++i) {
            if (Character.isLetter(sb.charAt(i))) {
                nsb.append(sb.charAt(i));
            }
        }
        String nres = "#" + nsb.toString();
        int len = Math.min(nres.length(), 100);
        return nres.substring(0, len);
    }
}
