package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.TimeLogger;
import edu.neu.coe.info6205.util.Utilities;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ThreeSumBenchmark {
    public ThreeSumBenchmark(int runs, int n, int m) {
        this.runs = runs;
        this.supplier = new Source(n, m).intsSupplier(10);
        this.n = n;
    }

    public void runBenchmarks() {
        System.out.println("ThreeSumBenchmark: N=" + n );
        benchmarkThreeSum("ThreeSumQuadratic", (xs) -> new ThreeSumQuadratic(xs).getTriples(), n, timeLoggersQuadratic);
        benchmarkThreeSum("ThreeSumQuadraticWithCalipers", (xs) -> new ThreeSumQuadraticWithCalipers(xs).getTriples(), n,timeLoggersQuadraticWithCalipers);
        benchmarkThreeSum("ThreeSumQuadrithmic", (xs) -> new ThreeSumQuadrithmic(xs).getTriples(), n, timeLoggersQuadrithmic);
        benchmarkThreeSum("ThreeSumCubic", (xs) -> new ThreeSumCubic(xs).getTriples(), n, timeLoggersCubic);

    }

    public static void main(String[] args) {
        new ThreeSumBenchmark(100, 250, 250).runBenchmarks();
        new ThreeSumBenchmark(50, 500, 500).runBenchmarks();
        new ThreeSumBenchmark(20, 1000, 1000).runBenchmarks();
        new ThreeSumBenchmark(10, 2000, 2000).runBenchmarks();
        new ThreeSumBenchmark(5, 4000, 4000).runBenchmarks();
        new ThreeSumBenchmark(3, 8000, 8000).runBenchmarks();
        new ThreeSumBenchmark(2, 16000, 16000).runBenchmarks();
    }

    private void benchmarkThreeSum(final String description, final Consumer<int[]> function, int n, final TimeLogger[] timeLoggers) {
        if (description.equals("ThreeSumCubic") && n > 4000) return;
        long bt = System.currentTimeMillis();

        function.accept(supplier.get());
        long at = System.currentTimeMillis();
        long time = at - bt;
        double d = time;
        double NorTime = d / n / n / Utilities.lg(n) * 1e6;
       System.out.println(description +":Raw time = " + d + " Normalized Time = " + NorTime );
//         System.out.println(d + "        "  + NorTime);

        // FIXME
        // END 
    }

    private final static TimeLogger[] timeLoggersCubic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^3): ", (time, n) -> time / n / n / n * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadrithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2 log n): ", (time, n) -> time / n / n / Utilities.lg(n) * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2): ", (time, n) -> time / n / n * 1e6)
    };

    private final static TimeLogger[] timeLoggersQuadraticWithCalipers ={
            new TimeLogger("Raw time per run (msec):",(time, n) -> time),
            new TimeLogger("Normalized time per run (n^2):", (time,n) -> time/ n/ n * 1e6)
    };

    private final int runs;
    private final Supplier<int[]> supplier;
    private final int n;
}
