public class MaxNumOfWordsFound {
    public int mostWordsFound(String[] sentences) {
        int res = 0;
        for(String s: sentences){
            String[] sp = s.split(" ");
            res = Math.max(res, sp.length);
        }
        return res;
    }
}
