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

    // essence: we can delete in a list with o(1) time if we know the position to delete. we can swap with the last
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
    // use swapping to make the remaining elements consecutive...
    Map<Integer, Set<Integer>> m = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    int rs = 0;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        boolean rt = true;
        if (m.containsKey(val)) {
            rt = false;
        }
        m.computeIfAbsent(val, k -> new HashSet<>()).add(rs);
        list.add(rs++, val);
        return rt;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        Set<Integer> posset = m.get(val);
        if (posset == null || posset.isEmpty() || rs <= 0) {
            return false;
        }
        // the end index is gone from the number's set anyway
        int endindex = rs - 1;
        Integer lastval = list.get(endindex);
        Set<Integer> endset = m.get(lastval);
        endset.remove(endindex);
        // if the one to delete is the last val, then we are done
        // otherwise we moved the last one to removepos and deleted endindex
        if (lastval != val) {
            Integer removepos = posset.iterator().next();
            posset.remove(removepos);
            endset.add(removepos);
            swap(list, removepos, endindex);
        }

        // correct the location of the swapped last
        rs--;
        return true;
    }

    Random rand = new Random();

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        //  System.out.println(list+" "+rs);
        int ran = rand.nextInt(rs);
        return list.get(ran);
    }

    void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}

