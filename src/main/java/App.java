public class App {

    public static void main(String[] args) {
        HashFunction hashFunction = new HashFunction();

        String input = "Example input";
        int hashValue = hashFunction.hash(input);
        System.out.println("The hash value for '" + input + "' is: " + hashValue);
    }
}
