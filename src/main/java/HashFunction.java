public class HashFunction implements Hash {
    @Override
   public int hash(String input) {
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            hash = (31 * hash + input.charAt(i)) % 256;
        }
        return hash;
    }
}
