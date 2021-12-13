import java.util.Collections;
import java.util.PriorityQueue;

public class SORTracker {

        private static class Item implements Comparable<Item>{
            String name;
            int score;
            Item(String name, int score){
                this.name = name;
                this.score = score;
            }

            @Override
            public int compareTo(Item y) {
                if(score!= y.score){
                    return Integer.compare(y.score, score);
                }else{
                    return name.compareTo(y.name);
                }
            }
        }
        PriorityQueue<Item> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Item> pq2 = new PriorityQueue<>();
        int next = 1;
        public SORTracker() {

        }

        public void add(String name, int score) {
            Item input = new Item(name, score);
            if(pq1.isEmpty()){
                pq1.offer(input);
            }
            else if(input.compareTo(pq1.peek())<=0){
                pq1.offer(input);
            }else{
                pq2.offer(input);
            }
            if(pq1.size()>next){
                pq2.offer(pq1.poll());
            }
        }

        public String get() {
            while(pq1.size()<next){
                pq1.offer(pq2.poll());
            }
            Item top = pq1.peek();
            ++next;
            return top.name;
        }


    public static void main(String[] args) {
        SORTracker tracker = new SORTracker(); // Initialize the tracker system.
        tracker.add("bradford", 2); // Add location with name="bradford" and score=2 to the system.
        tracker.add("branford", 3); // Add location with name="branford" and score=3 to the system.
        tracker.get();              // The sorted locations, from best to worst, are: branford, bradford.
        // Note that branford precedes bradford due to its higher score (3 > 2).
        // This is the 1st time get() is called, so return the best location: "branford".
        tracker.add("alps", 2);     // Add location with name="alps" and score=2 to the system.
        tracker.get();              // Sorted locations: branford, alps, bradford.
        // Note that alps precedes bradford even though they have the same score (2).
        // This is because "alps" is lexicographically smaller than "bradford".
        // Return the 2nd best location "alps", as it is the 2nd time get() is called.
        tracker.add("orland", 2);   // Add location with name="orland" and score=2 to the system.
        tracker.get();              // Sorted locations: branford, alps, bradford, orland.
        // Return "bradford", as it is the 3rd time get() is called.
        tracker.add("orlando", 3);  // Add location with name="orlando" and score=3 to the system.
        tracker.get();              // Sorted locations: branford, orlando, alps, bradford, orland.
        // Return "bradford".
        tracker.add("alpine", 2);   // Add location with name="alpine" and score=2 to the system.
        tracker.get();              // Sorted locations: branford, orlando, alpine, alps, bradford, orland.
        // Return "bradford".
        tracker.get();              // Sorted locations: branford, orlando, alpine, alps, bradford, orland.
        // Return "orland".
    }
}
