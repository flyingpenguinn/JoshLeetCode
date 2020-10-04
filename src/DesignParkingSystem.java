public class DesignParkingSystem {
    class ParkingSystem {

        private int[] map = new int[4];

        public ParkingSystem(int big, int medium, int small) {
            map[1] = big;
            map[2] = medium;
            map[3] = small;
        }

        public boolean addCar(int carType) {
            if (map[carType] <= 0) {
                return false;
            }
            map[carType]--;
            return true;
        }
    }
}
