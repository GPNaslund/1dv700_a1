import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HashSecurityTest {

    public void collisionResistanceTest(Hash hashFunction) {
        Set<Integer> hashSet = new HashSet<>();
        int attempts = 100000;
        int collisionCount = 0;

        for (int i = 0; i < attempts; i++) {
            String randomString = generateRandomString(10);
            int hashValue = hashFunction.hash(randomString);

            if (hashSet.contains(hashValue)) {
                collisionCount++;
            }

            hashSet.add(hashValue);
        }

        System.out.println("Total collisions found: " + collisionCount + " in " + attempts + " attempts.");
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
}