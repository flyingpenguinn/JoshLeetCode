import java.util.ArrayList;
import java.util.List;

/*
LC#1352
Implement the class ProductOfNumbers that supports two methods:

1. add(int num)

Adds the number num to the back of the current list of numbers.
2. getProduct(int k)

Returns the product of the last k numbers in the current list.
You can assume that always the current list has at least k numbers.
At any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.



Example:

Input
["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]

Output
[null,null,null,null,null,null,20,40,0,null,32]

Explanation
ProductOfNumbers productOfNumbers = new ProductOfNumbers();
productOfNumbers.add(3);        // [3]
productOfNumbers.add(0);        // [3,0]
productOfNumbers.add(2);        // [3,0,2]
productOfNumbers.add(5);        // [3,0,2,5]
productOfNumbers.add(4);        // [3,0,2,5,4]
productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
productOfNumbers.add(8);        // [3,0,2,5,4,8]
productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32


Constraints:

There will be at most 40000 operations considering both add and getProduct.
0 <= num <= 100
1 <= k <= 40000
 */
public class ProductOfLastKNumbers {
    // 2026-11-22 daily redo. First pass: per-value position lists + binary search (values 0..100), O(101 log n) per get.
    // This version: prefix products, reset to [1] on a 0 (0 cannot be divided back out). k >= size means a 0 is inside.
    // No overflow: the list since the last 0 is itself a contiguous sequence, which the statement guarantees fits in int.
    class ProductOfNumbers {
        private List<Integer> l = new ArrayList<>();

        public ProductOfNumbers() {
            l.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                l = new ArrayList<>();
                l.add(1);
            } else {
                l.add(l.get(l.size() - 1) * num);
            }
        }

        public int getProduct(int k) {
            if (k >= l.size()) {
                return 0;
            }
            int start = l.size() - k - 1;
            return l.get(l.size() - 1) / l.get(start);
        }
    }
}
