public class FindKeyOfNumbers {
    public int generateKey(int a, int b, int c) {
        String s1 = String.format("%04d", a);
        String s2 = String.format("%04d", b);
        String s3 = String.format("%04d", c);

        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            char c3 = s3.charAt(i);

            char minChar = (char) Math.min(c1, Math.min(c2, c3));

            key.append(minChar);
        }

        return Integer.parseInt(key.toString());
    }
}
