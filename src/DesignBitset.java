import java.util.HashSet;
import java.util.Set;

public class DesignBitset {
    class Bitset {

        private int size;
        private Set<Integer> setbits = new HashSet<>();
        private Set<Integer> unsetbits = new HashSet<>();

        public Bitset(int size) {
            this.size = size;
            for (int i = 0; i < size; ++i) {
                unsetbits.add(i);
            }
        }

        public void fix(int idx) {
            setbits.add(idx);
            unsetbits.remove(idx);
        }

        public void unfix(int idx) {
            unsetbits.add(idx);
            setbits.remove(idx);
        }

        public void flip() {
            Set<Integer> tmp = unsetbits;
            unsetbits = setbits;
            setbits = tmp;
        }

        public boolean all() {
            return setbits.size() == size;
        }

        public boolean one() {
            return !setbits.isEmpty();
        }

        public int count() {
            return setbits.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; ++i) {
                if (setbits.contains(i)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            return sb.toString();
        }
    }
}
