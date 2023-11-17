import java.util.Random;

public class UniformityTest {

    private Hash hashingFunction;

    public UniformityTest(Hash hashingFunction) {
        this.hashingFunction = hashingFunction;
    }

    private String generateRandomString(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder string = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            string.append(CHARACTERS.charAt(index));
        }

        return string.toString();
    }

    public int[] testUniformity(int numberOfTests) {
        int[] counts = new int[256];
        Random random = new Random();

        for (int i = 0; i < numberOfTests; i++) {
            String testString = generateRandomString(random.nextInt(50));
            int hashValue = hashingFunction.hash(testString);
            counts[hashValue]++;
        }

        return counts;
    }
}
