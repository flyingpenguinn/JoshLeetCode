public class GoatLatin {
    // trap is it could have caps letters too!
    private final String Vowels = "aeiouAEIOU";

    private boolean isVowel(char c){
        return Vowels.indexOf(c)!= -1;
    }

    private void pad(StringBuilder sb, int times){
        for(int i=0; i<times; i++){
            sb.append("a");
        }
    }

    public String toGoatLatin(String s) {
        if(s.isEmpty()){
            return s;
        }
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<ss.length;i++){
            if(isVowel(ss[i].charAt(0))){
                sb.append(ss[i]);
                sb.append("ma");
                pad(sb, i+1);
                sb.append(" ");
            }else{
                sb.append(ss[i].substring(1));
                sb.append(ss[i].charAt(0));
                sb.append("ma");
                pad(sb, i+1);
                sb.append(" ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
