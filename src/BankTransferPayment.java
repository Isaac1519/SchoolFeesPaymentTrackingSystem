/** Payment made via direct bank transfer or deposit. */
public class BankTransferPayment extends Payment {

    private final String bankName;
    private final String transferReference;

    public BankTransferPayment(String studentId, double amount, String bankName, String transferReference) {
        super(studentId, amount);
        this.bankName = bankName;
        this.transferReference = transferReference;
    }

    @Override
    public String getPaymentMethod() {
        return "Bank Transfer";
    }

    @Override
    public String getPaymentDetails() {
        return String.format("%s | Reference: %s", bankName, transferReference);
    }
}
