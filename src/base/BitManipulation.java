package base;

public class BitManipulation {
    static void main() {


        long num = 0;
        int bit = 31;
        // Set bit to 1
        num |= (1L << bit);

        // Clear bit to 0
        num &= ~(1L << bit);

        // Toggle bit
        num ^= (1L << bit);

        // Clear bit and all higher bits (keep lower bits)
        num &= (1L << bit) - 1;

        // Clear all lower bits (keep bit and higher bits)
        num &= ~((1L << bit) - 1);

        // Check if bit is set
        boolean set = (num & (1L << bit)) != 0;
    }
}
