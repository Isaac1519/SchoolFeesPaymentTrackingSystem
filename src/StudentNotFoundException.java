/** Thrown when a lookup is performed for a student ID that does not exist. */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
