public class NumberOfStringsAppearAsSubstring {
    public int numOfStrings(String[] patterns, String word) {
        int res = 0;
        for(String p: patterns){
            if(word.indexOf(p) != -1){
                res++;
            }
        }
        return res;
    }
}
