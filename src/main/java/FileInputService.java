import java.util.ArrayList;

public class FileInputService {

    public ArrayList<Student> readStudentFile(String filename) {

        ArrayList<Student> students = new ArrayList<>();
        students.addAll(readMultipleFiles(new String[]{filename}));
        return students;
    }

    public ArrayList<Student> readMultipleFiles(String[] filenames) {

        ThreadSafeGradeRepository repository = new ThreadSafeGradeRepository();
        ArrayList<StudentFileReader> threads = new ArrayList<>();
        ReportGenerator logger = new ReportGenerator();

        for (String filename : filenames) {
            StudentFileReader reader = new StudentFileReader(filename, repository);
            threads.add(reader);
            reader.start();
        }

        for (StudentFileReader t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                logger.generateErrorLog(
                        "InterruptedException: sleep interrupted",
                        "src/reports/errors.txt"
                );
            }
        }

        repository.setReadingFinished();

        ArrayList<Student> students = new ArrayList<>();
        Student s;

        while ((s = repository.getStudent()) != null) {
            students.add(s);
        }

        return students;
    }
}