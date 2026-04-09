import java.util.ArrayList;
import java.lang.Math;

public class GradeAnalyzer extends GradeProcessor {

    private double average;
    private double highest;
    private double lowest;
    private double stdDeviation;

    @Override
    public void processGrades(ArrayList<Double> grades) {

        if (grades == null || grades.isEmpty()) {
            average = highest = lowest = stdDeviation = 0;
            return;
        }

        double sum = 0;
        highest = grades.get(0);
        lowest = grades.get(0);

        for (double g : grades) {
            sum += g;
            if (g > highest) highest = g;
            if (g < lowest) lowest = g;
        }

        average = sum / grades.size();

        double variance = 0;
        for (double g : grades) {
            variance += Math.pow(g - average, 2);
        }

        stdDeviation = Math.sqrt(variance / grades.size());
    }

    public double getAverage() { return average; }
}