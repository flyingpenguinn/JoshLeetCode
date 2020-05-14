public class MaskingPersonalInfo {
    public String maskPII(String s) {
        if (s.contains("@")) {
            return maskEmail(s);
        } else {
            return maskPhone(s);
        }
    }

    private String maskPhone(String s) {
        s = s.replaceAll("[^0-9]", "");
        String local = "***-***-" + s.substring(s.length() - 4);
        if (s.length() == 10) {
            return local;
        } else {
            int clen = s.length() - 10;
            StringBuilder sb = new StringBuilder("+");
            for (int i = 0; i < clen; i++) {
                sb.append("*");
            }
            sb.append("-");
            return sb.toString() + local;
        }
    }

    private String maskEmail(String s) {
        s = s.toLowerCase();
        String[] parts = s.split("@");
        String firstname = parts[0];
        String converted = firstname.charAt(0) + "*****" + firstname.charAt(firstname.length() - 1);
        converted += "@" + parts[1];
        return converted;
    }

}
