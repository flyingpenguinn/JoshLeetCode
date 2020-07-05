import java.util.*;

/*
LC#381
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
 */
public class InsertDeleteGetrandomWithDuplicate {

    // essence: we can delete in a list with o(1) time if we know the position to delete. we can swap with the last and delete the last in o1
    // we can insert in O(1) time with array list concept
    // to pick from list with linear possibility we dont really need to sort the list
    // diff from #380 is we need to manage last == deleted situation
    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();
        rc.insert(1);
        rc.remove(1);
        rc.insert(1);

        for (int i = 0; i < 10; i++) {
            System.out.println(rc.getRandom());
        }

    }
}

class RandomizedCollection {

    private List<Integer> list = new ArrayList<>();
    private Map<Integer, Set<Integer>> lm = new HashMap<>();
    private int index = 0; // next position to insert
    /** Initialize your data structure here. */
    public RandomizedCollection() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        Set<Integer> l1 = lm.get(val);
        boolean nonExt = l1==null || l1.isEmpty();
        list.add(index, val);
        lm.computeIfAbsent(val, k-> new HashSet<>()).add(index);
        index++;
        return nonExt;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> l1 = lm.get(val);
        if(l1==null || l1.isEmpty()){
            return false;
        }
        int p1 = l1.iterator().next();
        int p2 = index-1; // can't be size-1
        int v2 = list.get(p2);
        Set<Integer> l2 = lm.get(v2);
        if(val == v2){
            l1.remove(p2); // just need to remove the last if they are equal
        }else{
            swap(list, p1, p2);
            l1.remove(p1);
            l2.remove(p2);
            l2.add(p1);
        }
        index--;
        return true;
    }

    private void swap(List<Integer> list, int p1, int p2){
        int v1 = list.get(p1);
        int v2 = list.get(p2);
        list.set(p2, v1);
        list.set(p1, v2);
    }

    private Random ran = new Random();
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(ran.nextInt(index));
    }
}

