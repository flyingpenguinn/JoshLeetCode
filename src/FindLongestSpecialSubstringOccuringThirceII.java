import java.util.*;

public class FindLongestSpecialSubstringOccuringThirceII {
    // 3 ways
    // 1. longest -2
    // 2. 3rd longest
    // 3. longest -1 or just 2nd longest. former if 2nd longest is long enough
    public int maximumLength(String s) {
        int n = s.length();
        List<Integer>[] v  = new ArrayList[26];
        for(int i=0; i<26; ++i) {
            v[i] = new ArrayList<>();
        }
        int i = 0;
        while(i<n){
            int j = i;
            while(j<n && s.charAt(j) == s.charAt(i)){
                ++j;
            }
            int len = j-i;
            int cind = s.charAt(i)-'a';

            v[cind].add(len);
            i = j;
        }
        int res = 0;
        for(i=0; i<26; ++i){
            List<Integer> chunks = v[i];
            if(chunks.isEmpty()){
                continue;
            }
            Collections.sort(chunks, Collections.reverseOrder());
            // cout<<"i="<<i<<" chunks="<<chunks.size()<<endl;
            int way1 = chunks.get(0)-2;
            int way2 = 0;
            if(chunks.size()>=2){
                int p1 = chunks.get(0);
                int p2 = chunks.get(1);
                if(p2>=p1-1){
                    way2 = p1-1;
                }else{
                    way2 = p2;
                }
            }
            int way3 = 0;
            if(chunks.size()>=3){
                int p3 = chunks.get(2);
                way3 = p3;
            }
            int cur = Math.max(way1, Math.max(way2, way3));
            res = Math.max(res, cur);
        }
        return res>0? res: -1;
    }

}
