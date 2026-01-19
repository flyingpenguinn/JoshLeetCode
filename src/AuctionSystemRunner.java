import java.util.*;

public class AuctionSystemRunner {
    static class U implements Comparable<U> {
        int userId;
        int amount;

        public U(int userId, int amount) {
            this.userId = userId;
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof U u)) return false;
            return userId == u.userId && amount == u.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, amount);
        }


        @Override
        public int compareTo(U o) {
            if (this.amount != o.amount) {
                return Integer.compare(o.amount, this.amount);
            } else {
                return Integer.compare(o.userId, this.userId);
            }
        }
    }

    static class AuctionSystem {
        private Map<Integer, TreeSet<U>> m = new HashMap<>();
        private Map<Integer, Map<Integer, Integer>> bm = new HashMap<>();

        public AuctionSystem() {

        }

        public void addBid(int userId, int itemId, int bidAmount) {
            Map<Integer,Integer> im = bm.getOrDefault(userId, new HashMap<>());
            if (im.containsKey(itemId)) {
                int oldv = im.get(itemId);
                removeValue(itemId, userId, oldv);
            }
            addValue(itemId, userId, bidAmount);
            im.put(itemId, bidAmount);
            bm.put(userId, im);
        }

        private void addValue(int itemId, int userId, int bidAmount) {
            U nu = new U(userId, bidAmount);
            m.computeIfAbsent(itemId, k -> new TreeSet<>()).add(nu);
        }

        private void removeValue(int itemId, int userId, int oldv) {
            U ou = new U(userId, oldv);
            if (m.containsKey(itemId)) {
                m.get(itemId).remove(ou);
                if (m.get(itemId).isEmpty()) {
                    m.remove(itemId);
                }
            }
        }

        public void updateBid(int userId, int itemId, int newAmount) {
            addBid(userId, itemId, newAmount);
        }

        public void removeBid(int userId, int itemId) {
            Map<Integer,Integer> im = bm.getOrDefault(userId, new HashMap<>());
            if (im.containsKey(itemId)) {
                int oldv = bm.get(userId).get(itemId);
                removeValue(itemId, userId, oldv);
                im.remove(itemId);
                if(im.isEmpty()){
                    bm.remove(userId);
                }
            }
        }

        public int getHighestBidder(int itemId) {
            if (!m.containsKey(itemId)) {
                return -1;
            }
            if (m.get(itemId).isEmpty()) {
                return -1;
            }
            return m.get(itemId).iterator().next().userId;
        }
    }


    static void main() {


        AuctionSystem auctionSystem = new AuctionSystem(); // Initialize the Auction system
        auctionSystem.addBid(1, 7, 5); // User 1 bids 5 on item 7
        auctionSystem.addBid(2, 7, 6); // User 2 bids 6 on item 7
        System.out.println(auctionSystem.getHighestBidder(7)); // return 2 as User 2 has the highest bid
        auctionSystem.updateBid(1, 7, 6); // User 1 updates bid to 8 on item 7
        System.out.println(auctionSystem.getHighestBidder(7)); // return 1 as User 1 now has the highest bid
        auctionSystem.removeBid(2, 7); // Remove User 2's bid on item 7
        System.out.println(auctionSystem.getHighestBidder(7)); // return 1 as User 1 is the current highest bidder
        System.out.println(auctionSystem.getHighestBidder(3)); // return -1 as no bids exist for item 3©leetcode
    }

}
