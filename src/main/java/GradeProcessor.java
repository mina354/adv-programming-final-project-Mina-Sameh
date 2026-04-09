import java.util.ArrayList;

public abstract class GradeProcessor {

    public abstract void processGrades(ArrayList<Double> grades);

    public void validateGrades(ArrayList<Double> grades) throws InvalidGradeException {
        for (double g : grades) {
            if (g < 0) {
                throw new InvalidGradeException("Grade " + g + " is below minimum allowed value 0");
            }
            if (g > 100) {
                throw new InvalidGradeException("Grade " + g + " is above maximum allowed value 100");
            }
        }
    }
}