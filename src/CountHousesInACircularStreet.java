public class CountHousesInACircularStreet {
    interface Street {
        public void openDoor();

        public void closeDoor();

        public boolean isDoorOpen();

        public void moveRight();

        public void moveLeft();
    }

    public int houseCount(Street street, int k) {
        for (int i = 0; i < k; ++i) {
            street.closeDoor();
            street.moveRight();
        }
        street.openDoor();
        street.moveRight();
        int res = 1;
        while (!street.isDoorOpen()) {
            street.moveRight();
            ++res;
        }
        return res;
    }

}
