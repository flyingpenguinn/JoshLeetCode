public class AddingSpaceToString {
    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        int si = 0;
        for(int i=0; i<s.length(); ++i){
            if(si<spaces.length && sb.length()-si == spaces[si]){
                sb.append(" ");
                ++si;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
