import java.util.*;

public class OrderManagementSystem {
    class OrderAtt {
        String type;
        int price;

        public OrderAtt(String type, int price) {
            this.type = type;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof OrderAtt orderAtt)) return false;
            return price == orderAtt.price && Objects.equals(type, orderAtt.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, price);
        }
    }

    class Order {
        int orderId;
        OrderAtt att;

        public Order(int orderId, OrderAtt att) {
            this.orderId = orderId;
            this.att = att;
        }
    }

    public OrderManagementSystem() {

    }

    private Map<Integer, Order> om1 = new HashMap<>();
    private Map<OrderAtt, Set<Integer>> om2 = new HashMap<>();


    public void addOrder(int orderId, String orderType, int price) {
        OrderAtt ot = new OrderAtt(orderType, price);
        Order co = new Order(orderId, ot);
        om1.put(orderId, co);
        om2.computeIfAbsent(ot, k -> new HashSet<>()).add(orderId);
    }

    public void modifyOrder(int orderId, int newPrice) {
        Order co = om1.get(orderId);
        cancelOrder(orderId);
        addOrder(orderId, co.att.type, newPrice);
    }

    public void cancelOrder(int orderId) {
        Order co = om1.get(orderId);
        om2.get(co.att).remove(orderId);
        om1.remove(orderId);
    }

    public int[] getOrdersAtPrice(String orderType, int price) {
        Set<Integer> cur = om2.getOrDefault(new OrderAtt(orderType, price), new HashSet<>());
        int[] res = new int[cur.size()];
        Iterator<Integer> iterator = cur.iterator();
        for (int i = 0; i < res.length; ++i) {
            res[i] = iterator.next();
        }
        return res;
    }
}