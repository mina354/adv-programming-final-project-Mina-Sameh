public class GradeCalculatorTask implements Runnable {

    private Student student;
    private ThreadSafeGradeRepository repository;

    public GradeCalculatorTask(Student student, ThreadSafeGradeRepository repository) {
        this.student = student;
        this.repository = repository;
    }

    @Override
    public void run() {

        GradeAnalyzer analyzer = new GradeAnalyzer();

        try {
            analyzer.validateGrades(student.getGrades());
            analyzer.processGrades(student.getGrades());

            repository.addProcessedResult(
                    student.getStudentId(),
                    analyzer.getAverage(),
                    student.getLetterGrade()
            );

        } catch (InvalidGradeException e) {
            new ReportGenerator().generateErrorLog(e.toString(), "src/reports/errors3.txt");
        }
    }
}