import java.util.HashSet;
import java.util.Set;

public class DesignPhoneDirectory {

}

class PhoneDirectory {

    int[] r;
    int[] m;
    int avail = 0;

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory(int maxNumbers) {
        r = new int[maxNumbers];
        m = new int[maxNumbers];
        for (int i = 0; i < r.length; i++) {
            r[i] = i;
            m[i] = i;
        }
        avail = maxNumbers;
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (avail == 0) {
            return -1;
        }
        int rt = r[0];

        m[r[0]] = avail - 1;
        m[r[avail - 1]] = 0;
        swap(r, 0, avail - 1);
        avail--;
        return rt;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return m[number] < avail;
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        if (check(number)) {
            return;
        }
        int np = m[number];
        m[r[avail]] = np;
        m[number] = avail;
        swap(r, avail, np);

        avail++;
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

class DesignPhoneDirectorySet {
    // similar to memory allocation in cpp! use a freelist
    // use a set to record the "holes". anything under used will be checked against the holes list.
    public DesignPhoneDirectorySet(int maxNumbers) {
        max = maxNumbers;
    }

    int max = 0;
    Set<Integer> released = new HashSet<>();

    int used = 0;

    public int get() {
        if (!released.isEmpty()) {
            Integer next = released.iterator().next();
            released.remove(next);
            return next;
        } else if (used < max) {
            return used++;
        } else {
            return -1;
        }
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return released.contains(number) || number >= used;
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        if (number < used) {
            released.add(number);
        }
    }
}

