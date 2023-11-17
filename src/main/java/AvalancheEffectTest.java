public class AvalancheEffectTest {

    private HashFunction hashFunction;

    public AvalancheEffectTest(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    public int[] testAvalancheEffect(String baseString, int numberOfTests) {
        int[] hammingDistances = new int[numberOfTests];
        int baseHash = hashFunction.hash(baseString);

        for (int i = 0; i < numberOfTests; i++) {
            String testString = flipOneBit(baseString, i % baseString.length());
            int modifiedHash = hashFunction.hash(testString);
            hammingDistances[i] = calculateHammingDistance(baseHash, modifiedHash);
        }

        return hammingDistances;
    }

    private String flipOneBit(String input, int position) {
        char[] chars = input.toCharArray();
        chars[position] ^= 1;
        return new String(chars);
    }

    private int calculateHammingDistance(int originalHash, int modifiedHash) {
        return Integer.bitCount(originalHash ^ modifiedHash);
    }
}