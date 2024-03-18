public class ExistenceOfSubInStringAndReverse {
    public boolean isSubstringPresent(String s) {
        StringBuilder sb = new StringBuilder(s);
        String rs = sb.reverse().toString();
        int n = s.length();
        for (int i = 0; i + 1 < n; ++i) {
            String sub = s.substring(i, i + 2);
            //  System.out.println(sub);
            if (s.indexOf(sub) != -1 && rs.indexOf(sub) != -1) {
                return true;
            }
        }
        return false;
    }
}
