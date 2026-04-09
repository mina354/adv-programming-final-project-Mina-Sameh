import java.util.ArrayList;
import java.lang.Thread;

public class Main {

    public static void main(String[] args) {

        ThreadSafeGradeRepository repository = new ThreadSafeGradeRepository();

        try {

            ReportGenerator rg = new ReportGenerator();
            Course course1 = new Course("CS101", "Advanced programming");
            rg.generateSummaryReport(course1, "C:/Users/Ahmed/output?.txt");

            ReportGenerator rg2 = new ReportGenerator();
            Course emptyCourse = new Course("TEST", "Empty");
            System.out.println("Students count: " + emptyCourse.getStudents().size());
            rg2.generateSummaryReport(emptyCourse, "src/reports/output.txt");

            StudentFileReader t1 = new StudentFileReader("src/data/students_1.txt", repository);
            StudentFileReader t2 = new StudentFileReader("src/data/students_2.txt", repository);
            StudentFileReader t3 = new StudentFileReader("src/data/students_3.txt", repository);

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            repository.setReadingFinished();

            ArrayList<Thread> workers = new ArrayList<>();

            while (true) {
                Student student = repository.getStudent();
                if (student == null) break;

                Thread worker = new Thread(new GradeCalculatorTask(student, repository));
                workers.add(worker);
                worker.start();
            }

            for (Thread t : workers) {
                t.join();
            }

            System.out.println("Processed Results:");
            repository.getResults().forEach((id, result) ->
                    System.out.println(id + " -> " + result)
            );

        } catch (Exception e) {
            new ReportGenerator().generateErrorLog(e.toString(), "src/reports/threaderrors.txt");
        }
    }
}