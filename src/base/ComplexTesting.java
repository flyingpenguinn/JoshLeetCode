package base;

public class ComplexTesting {
    public static void main(String[] args) {
        String teststr = "[CountIntervals,count,add,add,count,count,add]\n" +
                "[[]:[]:[39,44]:[13,49]:[]:[]:[47,50]]";
        int endPart1 = teststr.indexOf("]");
        String part1 = teststr.substring(1, endPart1);
        String[] part1s = part1.split(",");
        String part2 = teststr.substring(endPart1+3, teststr.length()-1);
        String[] part2s = part2.split(":");
        for(int i=0; i<part1s.length; ++i){
            String p1 = part1s[i];
            String p2 = part2s[i];
            if(p1.equals("CountIntervals")){
                continue;
            }else if(p1.equals("add")){
                // do something
            }
        }
    }
}
