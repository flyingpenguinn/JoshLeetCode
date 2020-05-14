public class SolveTheEquation {
    public String solveEquation(String e) {
        String[] sides= e.split("=");
        int[] rl= doside(sides[0]);
        int[] rr= doside(sides[1]);
        int xc= rl[0]-rr[0];
        int nc= rr[1]-rl[1];
        if(xc==0 && nc==0){
            return "Infinite solutions";
        }else if(xc==0){
            return "No solution";
        }else{
            return "x="+String.valueOf(nc/xc);
        }
    }

    int[] doside(String s){
        StringBuilder sb= new StringBuilder();
        int value=0;
        int nx=0;
        int delta=1;
        for(int i=0;i<=s.length();i++){
            char c= i==s.length()?'*':s.charAt(i);
            if(c=='+' || c=='-' || c=='*'){
                String block= sb.toString();

                if(block.endsWith("x")){
                    // plain x no number in front
                    String nxs= block.substring(0,block.length()-1);
                    int cof= nxs.isEmpty()?1: Integer.valueOf(nxs);
                    nx += delta*cof;
                }else{
                    // for -x-1
                    if(!block.isEmpty()){
                        value += delta* Integer.valueOf(block);
                    }
                }
                delta= (c=='+'?1:-1);

            }else{
                if(i>0 && (s.charAt(i-1)=='+' || s.charAt(i-1)=='-')){
                    sb= new StringBuilder();
                }
                sb.append(c);
            }
        }
        int[] r= new int[2];
        r[0]=nx;
        r[1]=value;
        return r;
    }
}
