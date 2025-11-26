public class ConvertNumberWordsToDigits {
    private String[] str = new String[] {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public String convertNumber(String s) {
        int n = s.length();
        int sn = str.length;
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i<n){
            boolean found = false;
            for(int j=0; j<sn; ++j){
                if(s.startsWith(str[j], i)){
                    sb.append(j);
                    i += str[j].length();
                    found = true;
                    break;
                }
            }
            if(!found){
                ++i;
            }
        }
        return sb.toString();
    }
}
