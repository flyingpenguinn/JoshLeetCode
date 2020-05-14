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

    class Rt {
        ListNode n;
        int c;

        public Rt(ListNode n, int c) {
            this.n = n;
            this.c = c;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int r1 = len(l1);
        int r2 = len(l2);
        Rt r = null;
        if (r1 >= r2) {
            r = dol(r1, r2, l1, l2);
        } else {
            r = dol(r2, r1, l2, l1);
        }
        if (r.c != 0) {
            ListNode nh = new ListNode(1);
            nh.next = r.n;
            return nh;
        } else {
            return r.n;
        }
    }

    // r1>=r2
    Rt dol(int r1, int r2, ListNode l1, ListNode l2) {
        if (r1 == 0) {
            return new Rt(null, 0);
        }
        Rt later = null;
        int v2 = 0;
        if (r1 > r2) {
            later = dol(r1 - 1, r2, l1.next, l2);
        } else {
            later = dol(r1 - 1, r2 - 1, l1.next, l2.next);
            v2 = l2.val;
        }
        int raw = l1.val + v2 + later.c;
        ListNode cur = new ListNode(raw % 10);
        cur.next = later.n;
        int carry = raw / 10;
        return new Rt(cur, carry);
    }

    int len(ListNode n) {
        return n == null ? 0 : 1 + len(n.next);
    }

    public static void main(String[] args) {

    }
}
