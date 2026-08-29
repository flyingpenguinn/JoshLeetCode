public class LexicographicallyLargestStringAfterPairTransformations {
    public String[] largestString(int[] a) {
        int n = a.length;
        String[] res = new String[n];
        int ri = 0;
        for (int ai : a) {
            res[ri++] = build(ai);
        }
        return res;
    }

    private String build(int n){
        StringBuilder sb = new StringBuilder();
        if(((n>>26)&1)==1){
            sb.append("z");
            sb.append("z");
        }
        for(int i=25; i>=0; --i){
            if(((n>>i)&1)==1){
                char cc = (char)(i+'a');
                sb.append(cc);
            }
        }
        return sb.toString();
    }
}
