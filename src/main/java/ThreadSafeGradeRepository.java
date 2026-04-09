import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeGradeRepository {

    private final Queue<Student> queue = new LinkedList<>();
    private final ConcurrentHashMap<String, String> results = new ConcurrentHashMap<>();
    private boolean finished = false;

    public synchronized void addStudent(Student student) {
        queue.add(student);
        notifyAll();
    }

    public synchronized Student getStudent() {

        while (queue.isEmpty() && !finished) {
            try {
                wait();
            } catch (InterruptedException e) {
                new ReportGenerator().generateErrorLog(
                        "InterruptedException: sleep interrupted",
                        "src/reports/errors1.txt"
                );
                Thread.currentThread().interrupt();
            }
        }

        return queue.poll();
    }

    public synchronized void setReadingFinished() {
        finished = true;
        notifyAll();
    }

    public void addProcessedResult(String id, double avg, String grade) {
        results.put(id, "Avg: " + avg + ", Grade: " + grade);
    }

    public ConcurrentHashMap<String, String> getResults() {
        return results;
    }
}