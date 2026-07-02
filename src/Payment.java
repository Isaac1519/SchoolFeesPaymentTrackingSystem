import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract representation of a single fee payment transaction.
 * Concrete subclasses (CashPayment, MobileMoneyPayment, BankTransferPayment)
 * each implement getPaymentDetails() differently — the classic example of
 * runtime polymorphism used throughout the reports/receipts screens.
 */
public abstract class Payment {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static int counter = 1000;

    private final String receiptNumber;
    private final String studentId;
    private final double amount;
    private final LocalDateTime dateTime;

    public Payment(String studentId, double amount) {
        this.receiptNumber = "RCT-" + (++counter);
        this.studentId = studentId;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getFormattedDate() {
        return dateTime.format(FMT);
    }

    /** Abstraction + Polymorphism: each payment channel describes itself differently. */
    public abstract String getPaymentMethod();

    public abstract String getPaymentDetails();

    @Override
    public String toString() {
        return String.format("%s | %s | GHS %.2f | %s | %s",
                receiptNumber, studentId, amount, getPaymentMethod(), getFormattedDate());
    }
}
