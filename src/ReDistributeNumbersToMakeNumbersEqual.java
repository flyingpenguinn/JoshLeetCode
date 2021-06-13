public class ReDistributeNumbersToMakeNumbersEqual {
    // 2 WAs in the contest. seriously?!!
    public boolean makeEqual(String[] words) {
        int[] count = new int[26];
        for(String w: words){
            for(char c: w.toCharArray()){
                count[c-'a']++;
            }
        }
        for(int c:count){
            if(c>0 && c%words.length != 0){
                // it's not == size, it's %size == 0 !
                return false;
            }
        }
        return true;
    }
}
