public class FinalValueOfVariableAfterOperations {
    public int finalValueAfterOperations(String[] operations) {
        int res = 0;
        for(String s: operations){
            if(s.indexOf("++")!= -1){
                ++res;
            }else{
                --res;
            }
        }
        return res;
    }
}
