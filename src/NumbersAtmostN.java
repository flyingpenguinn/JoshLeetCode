public class NumbersAtmostN {
    public int atMostNGivenDigitSet(String[] d, int n) {
        String str= String.valueOf(n);
        int sn= str.length();
        int dn= d.length;
        int c=0;
        for(int i=1;i<sn;i++){
            c += (int)Math.pow(dn,i);
        }
        //  System.out.println(c);
        c += calcn(str,d,0);
        return c;
    }

    int calcn(String str,String[] d,int i){
        int sn= str.length();
        if(i==sn){
            return 0;
        }
        int dn= d.length;
        int r=0;
        int si = str.charAt(i)-'0';
        int j=0;
        while(j<dn){
            int dj=Integer.valueOf(d[j]);
            if(dj<si){
                r += (int)Math.pow(dn,sn-1-i);
                j++;
            }else if(dj==si){
                // trap! last digit needs to be counted directly
                if(i==sn-1){
                    r++;
                }else{
                    r += calcn(str,d,i+1);
                }
                j++;
            }else{
                break;
            }
        }
        // System.out.println(i+" "+r);
        return r;
    }
}
