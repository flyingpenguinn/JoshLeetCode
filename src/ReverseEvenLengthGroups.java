import base.ListNode;
import base.Lists;

public class ReverseEvenLengthGroups {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode pre = null;
        ListNode p = head;

        int len = 1;
        while (p != null) {
            int clen = 0;
            ListNode prevPre = pre;
            while (clen < len && p != null) {
                pre = p;
                p = p.next;
                ++clen;
            }
            if (clen % 2 == 0) {
                ListNode[] rt = reverse(prevPre, p);
                prevPre.next = rt[0];
                rt[1].next = p;
                pre = rt[1];
            }
            ++len;
        }
        return head;
    }

    private ListNode[] reverse(ListNode p, ListNode q) {
        ListNode tail = p.next;
        ListNode head = null;
        ListNode pre = p;
        while (p != q) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        head = pre;
        return new ListNode[]{head, tail};
    }

    public static void main(String[] args) {
     //   System.out.println(new ReverseEvenLengthGroups().reverseEvenLengthGroups(Lists.stringToListNode("5->2->6->3->9->1->7->3->8->4")));
        System.out.println(new ReverseEvenLengthGroups().reverseEvenLengthGroups(Lists.stringToListNode("0->4->2->1->3")));
    }
}
