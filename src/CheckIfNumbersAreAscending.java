public class CheckIfNumbersAreAscending {
    public boolean areNumbersAscending(String s) {
        String[] ss = s.split(" ");
        Integer last = null;
        for(int i=0; i<ss.length; ++i){
            if(!Character.isDigit(ss[i].charAt(0))){
                continue;
            }
            int cur = Integer.valueOf(ss[i]);
            if(last==null){
                last = cur;
            }else{
                if(cur<=last){
                    return false;
                }
                last = cur;
            }
        }
        return true;
    }
}
