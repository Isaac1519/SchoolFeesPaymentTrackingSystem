/** Thrown when a payment would push a student's balance below zero. */
public class OverpaymentException extends Exception {
    public OverpaymentException(String message) {
        super(message);
    }
}
