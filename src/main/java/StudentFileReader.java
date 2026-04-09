import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentFileReader extends Thread {

    private String fileName;
    private ThreadSafeGradeRepository repository;

    public StudentFileReader(String fileName, ThreadSafeGradeRepository repository) {
        this.fileName = fileName;
        this.repository = repository;
    }

    @Override
    public void run() {

        ReportGenerator logger = new ReportGenerator();

        try {
            Scanner scanner = new Scanner(new File(fileName));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                try {
                    // Prevent NullPointerException
                    if (line == null) {
                        logger.generateErrorLog(
                                "NullPointerException: line is null",
                                "errors.txt"
                        );
                        continue;
                    }

                    String[] parts = line.split(",");

                    // Validate structure
                    if (parts.length < 2) {
                        logger.generateErrorLog(
                                "Invalid data format: " + line,
                                "errors.txt"
                        );
                        continue;
                    }

                    // Trim BEFORE validation
                    String id = parts[0].trim();
                    String name = parts[1].trim();

                    // Parse grades
                    ArrayList<Double> grades = new ArrayList<>();

                    for (int i = 2; i < parts.length; i++) {
                        try {
                            grades.add(Double.parseDouble(parts[i].trim()));
                        } catch (NumberFormatException e) {
                            logger.generateErrorLog(
                                    "NumberFormatException: Invalid grade in line: " + line,
                                    "errors.txt"
                            );
                        }
                    }

                    // Create student
                    Student student = new Student(id, name, grades);
                    repository.addStudent(student);

                } catch (InvalidDataException e) {
                    logger.generateErrorLog(e.toString(), "src/reports/errors.txt");
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            logger.generateErrorLog(e.toString(), "src/reports/errors.txt");
        }
        throw new RuntimeException(new FileProcessingException("File not found: " + fileName, e));
    } catch (Exception e) {
        throw new RuntimeException(new FileProcessingException("Error processing file: " + fileName, e));
    }
}