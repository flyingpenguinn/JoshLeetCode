import base.ListNode;

/*
LC#445
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = getlen(l1);
        int len2 = getlen(l2);
        ListNode res = null;
        if(len1<len2 ){
            res = doadd(l2, len2, l1, len1);
        }else{
            res = doadd(l1, len1, l2, len2);
        }
        if(res.val>=10){
            ListNode nres = new ListNode(1);
            res.val = res.val % 10;
            nres.next = res;
            return nres;
        }else{
            return res;
        }
    }

    private int getlen(ListNode n){
        return n==null?0: 1+getlen(n.next);
    }

    private ListNode doadd(ListNode l1, int len1, ListNode l2, int len2){
        if(len2==0 && len1==0){
            return null;
        }else if(len2==0){
            return l1;
        }else if(len1==0){
            return l2;
        }
        ListNode later = null;
        int raw = 0;
        if(len1>len2){
            later = doadd(l1.next, len1-1, l2, len2);
            raw = l1.val;
        }else{
            // =, minus both
            later = doadd(l1.next,len1-1, l2.next, len2-1);
            raw = l1.val+l2.val;
        }
        int carry = 0;
        if(later != null){
            carry = later.val/10;
            later.val = later.val%10;
        }
        raw += carry;
        ListNode cur = new ListNode(raw);
        cur.next = later;
        return cur;
    }
}
