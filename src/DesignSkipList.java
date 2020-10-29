import java.util.*;

import static java.lang.System.*;

/*
LC#1206
Design a Skiplist without using any built-in libraries.

A Skiplist is a data structure that takes O(log(n)) time to add, erase and search. Comparing with treap and red-black tree which has the same function and performance, the code length of Skiplist can be comparatively short and the idea behind Skiplists are just simple linked lists.

For example: we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it. The Skiplist works this way:


Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons

You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top layers, add , erase and search can be faster than O(n). It can be proven that the average time complexity for each operation is O(log(n)) and space complexity is O(n).

To be specific, your design should include these functions:

bool search(int target) : Return whether the target exists in the Skiplist or not.
void add(int num): Insert a value into the SkipList.
bool erase(int num): Remove a value in the Skiplist. If num does not exist in the Skiplist, do nothing and return false. If there exists multiple num values, removing any one of them is fine.
See more about Skiplist : https://en.wikipedia.org/wiki/Skip_list

Note that duplicates may exist in the Skiplist, your code needs to handle this situation.



Example:

Skiplist skiplist = new Skiplist();

skiplist.add(1);
skiplist.add(2);
skiplist.add(3);
skiplist.search(0);   // return false.
skiplist.add(4);
skiplist.search(1);   // return true.
skiplist.erase(0);    // return false, 0 is not in skiplist.
skiplist.erase(1);    // return true.
skiplist.search(1);   // return false, 1 has already been erased.


Constraints:

0 <= num, target <= 20000
At most 50000 calls will be made to search, add, and erase.
 */
public class DesignSkipList {

    class Skiplist {
        private Random rand = new Random();
        int size = 0;

        class ListNode {
            int val;
            ArrayList<ListNode> nexts = new ArrayList<>();

            public ListNode(int val) {
                this.val = val;
            }

            public String toString() {
                return val + "";
            }
        }


        ListNode head = new ListNode(Integer.MIN_VALUE);

        public Skiplist() {
            // first row empty
            head.nexts.add(null);
        }

        public boolean search(int target) {
            List<ListNode> points = findInsertionPoints(target);
            // always have the first row because we will have head all the time
            ListNode firstrow = getNext(points.get(0), 0);
            if (firstrow != null && firstrow.val == target) {
                return true;
            }
            return false;
        }

        private ListNode getNext(ListNode node, int level) {
            if (node == null) {
                return null;
            }
            if (node.nexts.size() <= level) {
                return null;
            }
            return node.nexts.get(level);
        }

        public void add(int num) {

            List<ListNode> points = findInsertionPoints(num);
            ListNode newelem = new ListNode(num);
            int n = points.size();
            int i = 0;
            for (; i < n; i++) {
                ListNode prev = points.get(i);
                ListNode next = getNext(prev, i);

                addToList(i, newelem, prev);
                addToList(i, next, newelem);
                int coin = rand.nextInt(2);
                if (coin == 1) {
                    break;
                }
            }
            if (i == n) {
                addToList(n, newelem, head);
            }
            size++;
            //  print();
        }

        private void addToList(int i, ListNode next, ListNode cur) {
            if (cur.nexts.size() == i) {
                cur.nexts.add(next);
            } else {
                cur.nexts.set(i, next);
            }
        }

        public boolean erase(int num) {
            // same as search, verify the first row
            List<ListNode> points = findInsertionPoints(num);
            ListNode firstrow = getNext(points.get(0), 0);
            if (firstrow == null || firstrow.val != num) {
                return false;
            }
            int n = points.size();
            int i = 0;
            for (; i < n; i++) {
                ListNode prev = points.get(i);
                ListNode next = getNext(prev, i);
                addToList(i, getNext(next, i), prev);
            }
            // head n-1 level next becomes empty
            if (i == n && head.nexts.get(n - 1) == null) {
                // shrink
                head.nexts.remove(head.nexts.size() - 1);
            }
            size--;
            //    print();
            return true;
        }

        private List<ListNode> findInsertionPoints(int target) {
            int levels = head.nexts.size();
            ListNode cur = head;
            List<ListNode> prevs = new ArrayList<>();
            for (int j = levels - 1; j >= 0; j--) {
                while (getNext(cur, j) != null) {
                    ListNode next = getNext(cur, j);
                    if (next != null && next.val < target) {
                        cur = next;
                    } else {
                        // next >= target or null, we've found insertion point as next to prev. break out and we will start with cur at the next level j-1
                        break;
                    }
                }
                prevs.add(cur);
            }
            // revesrse so that 0th row is at the top of prevs
            Collections.reverse(prevs);
            return prevs;
        }

        // debug only
        void print() {
            int levels = head.nexts.size();
            for (int i = 0; i < levels; i++) {
                System.out.print("lv " + i + " = ");
                ListNode node = head;
                while (getNext(node, i) != null) {
                    System.out.print(getNext(node, i).val + "->");
                    node = getNext(node, i);
                }
                System.out.println();
            }
        }

    }
}
