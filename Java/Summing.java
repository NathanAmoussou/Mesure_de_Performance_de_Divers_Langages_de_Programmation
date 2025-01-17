import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Summing {

    public static void main(String[] args) {
        int size = 1_000_000;
        int iterations = 1000;

        // Generate random data
        Random random = new Random();
        List<Integer> randomIntegers = random
            .ints(size, 0, 101)
            .boxed()
            .collect(Collectors.toList());
        List<Double> randomFloats = random
            .doubles(size, 0, 10)
            .boxed()
            .collect(Collectors.toList());

        // Benchmark integer summing
        BenchmarkResult intDefaultSummingResults = measureExecutionTimes(
            () -> {
                int sum = 0;
                for (int num : randomIntegers) {
                    sum += num;
                }
            },
            iterations
        );

        System.out.println("Integer Default Summing Results");
        printBenchmarkResults(intDefaultSummingResults);

        // Benchmark float summing
        BenchmarkResult floatDefaultSummingResults = measureExecutionTimes(
            () -> {
                double sum = 0;
                for (double num : randomFloats) {
                    sum += num;
                }
            },
            iterations
        );

        System.out.println("Float Default Summing Results");
        printBenchmarkResults(floatDefaultSummingResults);
    }

    public static BenchmarkResult measureExecutionTimes(
        Runnable func,
        int iterations
    ) {
        List<Long> times = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            func.run();
            long endTime = System.nanoTime();
            times.add(endTime - startTime);
        }

        long minTime = times.stream().min(Long::compareTo).orElse(0L);
        long maxTime = times.stream().max(Long::compareTo).orElse(0L);
        double avgTime = times
            .stream()
            .mapToLong(Long::longValue)
            .average()
            .orElse(0.0);

        return new BenchmarkResult(
            minTime / 1_000_000.0,
            maxTime / 1_000_000.0,
            avgTime / 1_000_000.0
        );
    }

    public static void printBenchmarkResults(BenchmarkResult result) {
        System.out.printf("Min Time: %.2f ms%n", result.getMinTime());
        System.out.printf("Avg Time: %.2f ms%n", result.getAvgTime());
        System.out.printf("Max Time: %.2f ms%n", result.getMaxTime());
        System.out.println();
    }

    // Helper class to store benchmark results
    static class BenchmarkResult {

        private final double minTime;
        private final double maxTime;
        private final double avgTime;

        public BenchmarkResult(double minTime, double maxTime, double avgTime) {
            this.minTime = minTime;
            this.maxTime = maxTime;
            this.avgTime = avgTime;
        }

        public double getMinTime() {
            return minTime;
        }

        public double getMaxTime() {
            return maxTime;
        }

        public double getAvgTime() {
            return avgTime;
        }
    }
}
