public class LexicoSmallestStringAfterSwap {
    public String getSmallestString(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        for(int i=0; i+1<n; ++i){
            int vi = c[i]-'0';
            int vj = c[i+1]-'0';
            if(vi>vj && vi%2== vj%2){
                c[i] = (char)('0'+vj);
                c[i+1] = (char)('0'+vi);
                break;
            }
        }
        return new String(c);
    }
}
