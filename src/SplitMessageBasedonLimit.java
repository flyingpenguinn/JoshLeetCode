public class SplitMessageBasedonLimit {
    // binary search the result
    public String[] splitMessage(String s, int limit) {
        int l = 1;
        int u = s.length();
        int n = s.length();
        //    System.out.println(n);
        // how many parts
        while(l<=u){
            int mid = l+(u-l)/2;
            int alllen = digits(mid);
            int i = 0;
            int cp = 0;
            while(i<n && cp<mid){
                int cplen = digits(cp+1);
                int suffix = 1+cplen+1+alllen+1;
                if(suffix>limit){
                    break;
                }
                int curlen = suffix;
                while(curlen<limit && i<n){
                    ++i;
                    ++curlen;
                }
                ++cp;
            }
            //  System.out.println(mid+" l="+l+" u="+u+" i="+i+" cp="+cp);
            if(cp<mid){
                u = mid-1;
            }else if(i<n){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        //    System.out.println(l+" vs "+u);
        if(l>n){
            return new String[0];
        }
        if(limit*l<n){
            return new String[0];
        }
        return build(s, l, limit);
    }

    private String[] build(String s, int k, int limit){
        int i = 0;
        int cp = 1;
        int n = s.length();
        String[] res = new String[k];
        int ri = 0;
        while(i<n){
            String suffix = "<"+cp+"/"+k+">";
            int slen = suffix.length();
            StringBuilder sb = new StringBuilder();
            int clen = slen;
            while(i<n && clen < limit){
                sb.append(s.charAt(i));
                ++i;
                ++clen;
            }
            ++cp;
            if(ri>=res.length){
                return new String[0];
            }
            res[ri++] = sb.toString()+suffix;
        }
        return res;
    }

    private int digits(int n){
        int res = 0;
        while(n>0){
            n/=10;
            ++res;
        }
        return res;
    }
}
