package base;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists {

    public static ListNode stringToListNode(String str) {
        String[] array = str.split("->");
        ListNode head = new ListNode(-1);
        ListNode ptr = head;
        for (String a : array) {
            ListNode newNode = new ListNode(Integer.valueOf(a.trim()));
            ptr.next = newNode;
            ptr = newNode;
        }
        return head.next;
    }

    // strings like
    /*

    [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

    to list<list>
     */
    public static List<List<String>> stringToLists(String s) {
        List<List<String>> r = new ArrayList<>();
        String inList = s.substring(1, s.length() - 1);
        int start = 0;
        while (true) {
            int starting = inList.indexOf("[", start);
            if (starting == -1) {
                break;
            }
            int closing = inList.indexOf("]", start);
            String inner = inList.substring(starting + 1, closing);
            String[] strs = inner.split(",");

            r.add(Arrays.asList(strs));
            start = closing + 1;
        }
        return r;
    }

    public static List<List<Integer>> intArrayToList(int[][] array) {
        List<List<Integer>> r = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int[] ai = array[i];
            List<Integer> li = new ArrayList<>();
            for (int a : ai) {
                li.add(a);
                ;
            }
            r.add(li);
        }
        return r;
    }

    public static void printLinkedList(ListNode l) {
        ListNode ptr = l;
        while (ptr != null) {
            System.out.print(ptr.val);
            if (ptr.next != null) System.out.print(" -> ");
            ptr = ptr.next;
        }
        System.out.println();
    }

}
