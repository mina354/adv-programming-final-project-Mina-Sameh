import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ReportGenerator {

    // Composition (REQUIRED)
    private FileWriter fileWriter;

    // Summary Report
    public void generateSummaryReport(Course course, String outputFile) {
        try {
            fileWriter = new FileWriter(outputFile);
            PrintWriter writer = new PrintWriter(fileWriter);

            writer.println("===== SUMMARY REPORT =====");
            writer.println("Total Students: " + course.getStudents().size());

            try {
                writer.println("Class Average: " + course.getClassAverage());
            } catch (ArithmeticException e) {
                writer.println("Class Average: " + e.toString());
            }

            writer.close();

        } catch (IOException e) {
            generateErrorLog(e.toString(), "src/reports/errors.txt");
        }
    }

    // ✅ Grade Distribution
    public void generateGradeDistribution(Course course, String outputFile) {
        try {
            fileWriter = new FileWriter(outputFile, true);
            PrintWriter writer = new PrintWriter(fileWriter);

            HashMap<String, Integer> map = new HashMap<>();

            for (Student s : course.getStudents()) {
                String grade = s.getLetterGrade();
                map.put(grade, map.getOrDefault(grade, 0) + 1);
            }

            writer.println("\n===== GRADE DISTRIBUTION =====");

            for (String grade : map.keySet()) {
                writer.println(grade + ": " + map.get(grade));
            }

            writer.close();

        } catch (IOException e) {
            generateErrorLog(e.toString(), "src/reports/errors.txt");
        }
    }

    // Error Logging
    public void generateErrorLog(String errorMessage, String logFile) {
        try {
            fileWriter = new FileWriter(logFile, true);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.println(errorMessage);
            writer.close();

        } catch (IOException e) {
            System.out.println("Logging failed");
        }
    }
}