public class MinBucketsRequiredForWater {
    public int minimumBuckets(String str) {
        int n = str.length();
        int res = 0;
        char[] s = str.toCharArray();
        for(int i=0; i<n; ++i){
            if(s[i]=='.'){
                if(i-1>=0 && i+1<n && s[i-1] == 'H' && s[i+1] == 'H'){
                    s[i-1] = 'G';
                    s[i+1] = 'G';
                    ++res;
                }
            }
        }
        for(int i=0; i<n; ++i){
            if(s[i]=='.'){
                if(i-1>=0 && s[i-1]=='H'){
                    s[i-1] = 'G';
                    ++res;
                }else if(i+1<n && s[i+1] == 'H'){
                    s[i+1] = 'G';
                    ++res;
                }
            }
        }
        for(int i=0; i<n; ++i){
            if(s[i]=='H'){
                return -1;
            }
        }
        return res;
    }
}
