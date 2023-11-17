public class App {

    public static void main(String[] args) {
        HashFunction hashFunction = new HashFunction();
        UniformityTest uniformityTest = new UniformityTest(hashFunction);
        StatisticalAnalysis statisticalAnalysis = new StatisticalAnalysis();
        HashSecurityTest hashSecurityTest = new HashSecurityTest();


        int[] counts = uniformityTest.testUniformity(2000);

        double mean = statisticalAnalysis.calculateMean(counts);
        double standardDeviation = statisticalAnalysis.calculateStandardDeviation(counts);
        double chiSquare = statisticalAnalysis.performChiSquaredTest(counts);

        System.out.println("The mean of the counts: " + mean);
        System.out.println("The standard deviation: " + standardDeviation);
        System.out.println("The chi square: " + chiSquare);

        String currentWorkingDir = System.getProperty("user.dir");

        statisticalAnalysis.writeStatisticsToFile(counts, currentWorkingDir);

        System.out.println("=======================");
        AvalancheEffectTest avalancheEffectTest = new AvalancheEffectTest(hashFunction);
        String baseString = "testing_the_avalanche_effect";
        int[] hammingDistances = avalancheEffectTest.testAvalancheEffect(baseString, 1000);
        double avalancheMean = statisticalAnalysis.calculateMean(hammingDistances);
        double avalancheStdDev = statisticalAnalysis.calculateStandardDeviation(hammingDistances);

        System.out.println("Avalanche effect test: ");
        System.out.println("Mean hamming distance: " + avalancheMean);
        System.out.println("Standard deviation of hamming distances: " + avalancheStdDev);

        statisticalAnalysis.writeAvalancheDataToFile(hammingDistances, currentWorkingDir);

        hashSecurityTest.collisionResistanceTest(hashFunction);
    }
}
