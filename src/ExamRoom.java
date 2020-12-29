import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/*
LC#855
In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.



Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.
​​​​​​​

Note:

1 <= N <= 10^9
ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 */
class ExamRoom {
    private List<Integer> a = new ArrayList<>();
    private int n;
    public ExamRoom(int n) {
        this.n = n;
    }

    private int addandreturn(int v, int i){
        a.add(i, v);
        return v;
    }

    public int seat() {
        //    System.out.println(a);
        if(a.isEmpty()){
            return addandreturn(0,0);
        }
        int diff0 = a.get(0);
        int diffn = n-1- a.get(a.size()-1);

        int maxdiff = -1;
        int maxpoint = -1;
        int maxindex = -1;
        for(int i=0; i+1<a.size();i++){
            int cur = a.get(i);
            int next = a.get(i+1);
            int mid = (cur+next)/2;
            int dist = Math.min(mid-cur, next-mid);
            if(dist>maxdiff){
                maxdiff = dist;
                maxpoint = mid;
                maxindex = i+1;
            }
        }
        //   System.out.println(diff0+" "+diffn+" "+maxdiff);
        if(diff0>=maxdiff && diff0>=diffn){
            return addandreturn(0,0);
        }else if(maxdiff > diff0 && maxdiff >= diffn){
            return addandreturn(maxpoint, maxindex);
        }else{
            return addandreturn(n-1, a.size());
        }
    }

    public void leave(int p) {
        a.remove(Integer.valueOf(p));
    }
}


