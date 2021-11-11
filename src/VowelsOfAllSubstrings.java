public class VowelsOfAllSubstrings {

    private String vowels = "aeiou";
    private boolean isvowel(char c){
        return vowels.indexOf(c) != -1;
    }
    public long countVowels(String w) {
        int n = w.length();
        long cur = 0;
        long last = 0;
        long res = 0;
        for(int i=0; i<n; ++i){
            if(isvowel(w.charAt(i))){
                cur = last+i+1;
            }else{
                cur = last;
            }
            res += cur;
            last = cur;
        }
        return res;
    }
}
