/**
 * Tracks how much a single student owes and how much has been paid so far.
 * Encapsulates the balance so it can only change through controlled methods.
 */
public class FeeInvoice {

    private final String studentId;
    private double totalFeesDue;
    private double amountPaid;

    public FeeInvoice(String studentId, double totalFeesDue) {
        this.studentId = studentId;
        this.totalFeesDue = totalFeesDue;
        this.amountPaid = 0.0;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getTotalFeesDue() {
        return totalFeesDue;
    }

    public void setTotalFeesDue(double totalFeesDue) {
        this.totalFeesDue = totalFeesDue;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getBalance() {
        return totalFeesDue - amountPaid;
    }

    public boolean isFullyPaid() {
        return getBalance() <= 0.0;
    }

    /**
     * Applies a payment to this invoice.
     * @throws InvalidAmountException if the amount is not positive
     * @throws OverpaymentException   if the payment would exceed the outstanding balance
     */
    public void applyPayment(double amount) throws InvalidAmountException, OverpaymentException {
        if (amount <= 0) {
            throw new InvalidAmountException("Payment amount must be greater than zero.");
        }
        if (amount > getBalance()) {
            throw new OverpaymentException(
                    String.format("Payment of GHS %.2f exceeds outstanding balance of GHS %.2f.", amount, getBalance()));
        }
        amountPaid += amount;
    }
}
