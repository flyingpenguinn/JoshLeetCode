public class SortSentences {
    class Solution {
        public String sortSentence(String s) {
            String[] ss = s.split(" ");
            String[] res = new String[ss.length+1];
            for(String str: ss){
                int index = str.charAt(str.length()-1)-'0';
                res[index] = str.substring(0, str.length()-1);
            }
            StringBuilder sb = new StringBuilder();
            for(int i=1; i<res.length; i++){
                if(sb.length()>0){
                    sb.append(" ");
                }
                sb.append(res[i]);
            }
            return sb.toString();
        }
    }
}
