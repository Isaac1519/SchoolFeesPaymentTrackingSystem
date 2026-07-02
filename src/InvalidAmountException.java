/** Thrown when a monetary amount entered by the user is invalid (zero, negative, non-numeric). */
public class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}
