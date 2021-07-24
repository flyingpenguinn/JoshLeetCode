public class CheckIfAllCharsHaveEqualNumOccr {
    public boolean areOccurrencesEqual(String s) {
        int[] count = new int[26];
        for(char c: s.toCharArray()){
            count[c-'a']++;
        }
        int only = -1;
        for(int i=0; i<26; i++){
            if(count[i]>0){
                if(only==-1){
                    only = count[i];
                }else if(only != count[i]){
                    return false;
                }
            }
        }
        return true;
    }
}
