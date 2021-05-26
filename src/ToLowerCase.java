public class ToLowerCase {
    // not using tolower...
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length();i++){
            char c = str.charAt(i);
            if(c>='A' && c<='Z'){
                int diff = c -'A';
                char lower = (char) ('a' + diff);
                sb.append(lower);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
