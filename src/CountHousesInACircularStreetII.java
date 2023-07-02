public class CountHousesInACircularStreetII {
    interface Street {
        public void openDoor();

        public void closeDoor();

        public boolean isDoorOpen();

        public void moveRight();

        public void moveLeft();
    }

    public int houseCount(Street street, int k) {
        int first = -1;
        int res = 0;
        for (int i = 0; i <= 2 * k; ++i) {
            if (street.isDoorOpen()) {
                if (first == -1) {
                    first = i;
                } else {
                    res = i - first;
                    street.closeDoor();
                }

            }
            street.moveRight();
        }
        return res;
    }
}
