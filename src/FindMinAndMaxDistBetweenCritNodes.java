import base.ListNode;

public class FindMinAndMaxDistBetweenCritNodes {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        ListNode p = head;
        ListNode prev = null;
        int index = 0;
        int min = Integer.MAX_VALUE;
        int first = -1;
        int last = -1;
        int pcrit = -1;
        while(p!= null){
            ListNode next = p.next;
            if(prev != null && next != null){
                boolean cond1 = p.val > prev.val && p.val > next.val;
                boolean cond2 = p.val < prev.val && p.val < next.val;
                if(cond1 || cond2){
                    if(pcrit==-1){
                        first = index;
                    }else{
                        min = Math.min(min, index-pcrit);
                    }
                    pcrit = index;
                    last = index;
                }
            }
            prev = p;
            p = next;
            ++index;
        }
        if(last==first){
            return new int[]{-1, -1};
        }else{
            return new int[]{min, last-first};
        }
    }
}
