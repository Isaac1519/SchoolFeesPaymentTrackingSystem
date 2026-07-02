/** Thrown when an attempt is made to register a student ID that already exists. */
public class DuplicateStudentException extends Exception {
    public DuplicateStudentException(String message) {
        super(message);
    }
}
