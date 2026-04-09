import java.util.ArrayList;

public class Course {

    private String courseCode;
    private String courseName;
    private ArrayList<Student> students;

    // Constructor
    public Course(String courseCode, String courseName) throws InvalidDataException {

        if (courseCode == null || courseCode.isEmpty()) {
            throw new InvalidDataException("Course Code cannot be empty");
        }

        if (courseName == null || courseName.isEmpty()) {
            throw new InvalidDataException("Course Name cannot be empty");
        }

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }

    // Association: Course uses Student objects
    public synchronized void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    // Calculate class average
    public double getClassAverage() {
        if (students.isEmpty()) {
            throw new ArithmeticException("/ by zero");
        }

        double sum = 0;
        for (Student s : students) {
            sum += s.calculateAverage();
        }

        return sum / students.size();
    }

    // Get top student
    public Student getTopStudent() {
        if (students.isEmpty()) return null;

        Student top = students.get(0);

        for (Student s : students) {
            if (s.calculateAverage() > top.calculateAverage()) {
                top = s;
            }
        }

        return top;
    }

    // Generate full grade report
    public String generateGradeReport() {

        StringBuilder report = new StringBuilder();

        report.append("Course: ").append(courseName).append("\n");
        report.append("Code: ").append(courseCode).append("\n\n");

        for (Student s : students) {
            report.append(s.toString()).append("\n");
        }

        try {
            report.append("\nClass Average: ").append(getClassAverage());
        } catch (ArithmeticException e) {
            report.append("\nClass Average: ").append(e.toString());
        }

        Student top = getTopStudent();
        if (top != null) {
            report.append("\nTop Student: ")
                    .append(top.getStudentId())
                    .append(" - ")
                    .append(top.getName())
                    .append(" (")
                    .append(top.calculateAverage())
                    .append(")");
        }

        return report.toString();
    }

    // Getter
    public ArrayList<Student> getStudents() {
        return students;
    }
}