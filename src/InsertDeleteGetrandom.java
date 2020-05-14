import java.util.*;

/*
LC#380
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
 */
public class InsertDeleteGetrandom {
    // swap with the last element for deleted items.
    public static void main(String[] args) {
        // Init an empty set.
        RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
        randomSet.insert(3);

// Returns false as 2 does not exist in the set.
        randomSet.insert(3);

// Inserts 2 to the set, returns true. Set now contains [1,2].
        randomSet.insert(1);
        randomSet.remove(3);


// Since 2 is the only number in the set, getRandom always return 2.
        System.out.println(randomSet.getRandom());

    }
}


class RandomizedSet {
    // use swapping to make the remaining elements consecutive...
    Map<Integer, Integer> m = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    int rs = 0;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (m.containsKey(val)) {
            return false;
        }
        if (rs == list.size()) {
            m.put(val, rs);
            list.add(val);
            rs++;
        } else {
            m.put(val, rs);
            list.set(rs++, val);
        }
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        Integer pos = m.get(val);
        if (pos == null) {
            return false;
        }
        swap(list, pos, rs - 1);
        m.put(list.get(pos), pos);
        // correct the location of the swapped last
        rs--;
        m.remove(val);

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