public class AddTwoPolynomialsAsLinkedList {

    class PolyNode {
        int coefficient, power;
        PolyNode next = null;

        PolyNode() {
        }

        PolyNode(int x, int y) {
            this.coefficient = x;
            this.power = y;
        }

        PolyNode(int x, int y, PolyNode next) {
            this.coefficient = x;
            this.power = y;
            this.next = next;
        }

        public PolyNode addPoly(PolyNode p1, PolyNode p2) {
            PolyNode head = new PolyNode();
            PolyNode p = head;
            while (p1 != null && p2 != null) {
                PolyNode np = null;
                if (p1.power == p2.power) {
                    int newco = p1.coefficient + p2.coefficient;
                    if (newco != 0) {
                        np = new PolyNode(newco, p1.power);
                    }
                    p1 = p1.next;
                    p2 = p2.next;
                } else if (p1.power < p2.power) {
                    np = p2;
                    p2 = p2.next;
                } else {
                    np = p1;
                    p1 = p1.next;
                }
                if (np != null) {
                    p.next = np;
                    p = p.next;
                }
            }
            p.next = p1 == null ? p2 : p1;
            return head.next;
        }
    }
}
