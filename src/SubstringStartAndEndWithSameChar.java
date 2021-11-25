public class SubstringStartAndEndWithSameChar {
    long numberOfSubstrings(String s) {
        int[] count = new int[26];
        long res = 0;
        for(char c: s.toCharArray()){
            int cind = c-'a';
            ++count[cind];
            res += count[cind];
        }
        return res;
    }
}
