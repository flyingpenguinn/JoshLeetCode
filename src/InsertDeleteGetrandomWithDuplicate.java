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

    private Map<Integer, Set<Integer>> pm = new HashMap<>();
    private List<Integer> list = new ArrayList<>();

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {

    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        boolean ext = pm.containsKey(val);
        int pos = list.size();
        pm.computeIfAbsent(val, k -> new HashSet<>()).add(pos);
        list.add(val);
        return !ext;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int v1) {
        Set<Integer> set1 = pm.get(v1);
        if (set1 == null || set1.isEmpty()) {
            // dont forget empty processing
            return false;
        }
        int p2 = list.size() - 1;
        int v2 = list.get(p2);
        Set<Integer> set2 = pm.get(v2);
        if(v1==v2){
            removeLast(list);
            removeFromMap(pm, v2, p2);
            return true;
        }
        int p1 = pm.get(v1).iterator().next();
        swap(list, p1, p2); // dont forget swapping it!
        removeFromMap(pm, v1, p1);
        set2.add(p1);// must add first otherwise we may be adding on a deleted set
        removeFromMap(pm, v2, p2);
        removeLast(list);
        return true;
    }

    private void removeFromMap( Map<Integer,Set<Integer>> pm, int key, int val){
        Set<Integer> cur = pm.get(key);
        cur.remove(val);
        if(cur.isEmpty()){
            pm.remove(key);
        }

    }
    private void removeLast(List<Integer> list){
        list.remove(list.size()-1);
    }

    private void swap(List<Integer> list, int i, int j) {
        int vi = list.get(i);
        int vj = list.get(j);
        list.set(i, vj);
        list.set(j, vi);
    }

    private Random ran = new Random();

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        if (list.isEmpty()) {
            return -1;
        }
        return list.get(ran.nextInt(list.size()));
    }
}

