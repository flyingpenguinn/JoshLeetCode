import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StockPriceFluctuation {
    class StockPrice {
        private TreeMap<Integer, Integer> m = new TreeMap<>();
        private TreeMap<Integer, Integer> prices = new TreeMap<>();

        public StockPrice() {

        }

        public void update(int timestamp, int price) {

            if(m.containsKey(timestamp)){
                updatem(prices, m.get(timestamp), -1);
            }
            updatem(prices, price, 1);
            m.put(timestamp, price);
        }

        public int current() {
            return m.get(m.lastKey());
        }

        public int maximum() {
            return prices.lastKey();
        }

        public  int minimum() {
            return prices.firstKey();
        }

        private void updatem(TreeMap<Integer,Integer> m, int k, int d){
            int nv = m.getOrDefault(k, 0)+d;
            if(nv<=0){
                m.remove(k);
            }else{
                m.put(k, nv);
            }
        }
    }
}
