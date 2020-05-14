public class BulbSwitcherII {
    /*
As before, the first 6 lights uniquely determine the rest of the lights. This is because every operation that
modifies the xx-th light also modifies the (x+6)(x+6)-th light, so the xx-th light is always equal to the (x+6)(x+6)-th light.
    Actually, the first 3 lights uniquely determine the rest of the sequence, as shown by the table below for performing the operations a, b, c, d:

Light 1 = 1 + a + c + d
Light 2 = 1 + a + b
Light 3 = 1 + a + c
Light 4 = 1 + a + b + d
Light 5 = 1 + a + c
Light 6 = 1 + a + b
So 1,2,3 decided the other

Light 4 = (Light 1) + (Light 2) + (Light 3)
Light 5 = Light 3
Light 6 = Light 2

     */
    public int flipLights(int n, int m) {
        if (m == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            if (m == 1) {
                return 3;
            } else {
                return 4;
            }
        }
        if (n >= 3) {

            if (m == 1) {
                return 4;
            }
            if (m == 2) {
                return 7;
            }
            if (m == 3) {
                return 8;
            }

            return 8;
        }
        return -1;
    }
}
