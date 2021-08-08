public class CheckIfStringIsPrefixOfArray {
    public boolean isPrefixString(String s, String[] words) {
        int i = 0;
        int j = 0;
        int k = 0;
        for(; i<s.length() && j<words.length; ++i){
            if(s.charAt(i)==words[j].charAt(k)){
                ++k;
                if(k==words[j].length()){
                    ++j;
                    k=0;
                }
            }else{
                return false;
            }
        }
        return i==s.length() && k==0;
    }
}
