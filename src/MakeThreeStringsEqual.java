public class MakeThreeStringsEqual {
    public int findMinimumOperations(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        int cn = Math.min(n1, Math.min(n2, n3));
        int res = 0;
        while(n1>cn){
            --n1;
            ++res;
        }
        while(n2>cn){
            --n2;
            ++res;
        }
        while(n3>cn){
            --n3;
            ++res;
        }
        for(int i=0; i<cn; ++i){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            char c3 = s3.charAt(i);
            if(c1 != c2 || c2!= c3 || c1!= c3){
                if(i==0){
                    return -1;
                }else{
                    res += 3*(cn-i);
                    break;
                }
            }
        }
        return res;
    }
}
