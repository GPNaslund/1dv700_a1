import org.apache.commons.math3.stat.inference.ChiSquareTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class StatisticalAnalysis {
    public double calculateMean(int[] counts) {
        double sum = 0.0;
        for (int count : counts) {
            sum += count;
        }
        return sum / counts.length;
    }

    public double calculateStandardDeviation(int[] counts) {
        double sum = 0.0;
        double mean = calculateMean(counts);

        for (int count : counts) {
            sum += Math.pow(count - mean, 2);
        }
        double variance = sum / counts.length;
        return Math.sqrt(variance);
    }

    public double performChiSquaredTest(int[] counts) {
        long[] observed = new long[counts.length];
        double[] expected = new double[counts.length];
        double total = Arrays.stream(counts).sum();
        Arrays.fill(expected, total / counts.length);

        for (int i = 0; i < counts.length; i++) {
            observed[i] = (long) counts[i];
        }

        ChiSquareTest chiSquareTest = new ChiSquareTest();
        return chiSquareTest.chiSquare(expected, observed);
    }

    public void writeStatisticsToFile(int[] counts, String statsFilePath) {
        try (FileWriter writer = new FileWriter(statsFilePath + "/stats.csv")) {
            writer.append("Bucket,Count\n");

            for (int i = 0; i < counts.length; i++) {
                writer.append(String.valueOf(i)).append(",").append(String.valueOf(counts[i])).append("\n");
            }

            // Write the statistics
            writer.append("\n"); // Leave a blank line after the counts
            writer.append("Mean,").append(String.valueOf(calculateMean(counts))).append("\n");
            writer.append("Standard Deviation,").append(String.valueOf(calculateStandardDeviation(counts))).append("\n");
            writer.append("Chi-Square,").append(String.valueOf(performChiSquaredTest(counts))).append("\n");

        } catch (IOException e) {
            System.err.println("Could not save CSV file");
            System.err.println(e.getMessage());
        }
    }

    public void writeAvalancheDataToFile(int[] hammingDistances, String filePath) {
        try (FileWriter writer = new FileWriter(filePath + "/avalanche_data.csv")) {
            writer.append("Test Number,Hamming Distance\n");

            for (int i = 0; i < hammingDistances.length; i++) {
                writer.append(String.valueOf(i + 1)).append(",").append(String.valueOf(hammingDistances[i])).append("\n");
            }


            writer.append("\n");
            writer.append("Mean Hamming Distance,").append(String.valueOf(calculateMean(hammingDistances))).append("\n");
            writer.append("Standard Deviation,").append(String.valueOf(calculateStandardDeviation(hammingDistances))).append("\n");
        } catch (IOException e) {
            System.err.println("Could not save Avalanche data CSV file");
            System.err.println(e.getMessage());
        }
    }


}
