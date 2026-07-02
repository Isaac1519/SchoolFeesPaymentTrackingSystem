/** Payment made physically over the counter. */
public class CashPayment extends Payment {

    private final String cashierName;

    public CashPayment(String studentId, double amount, String cashierName) {
        super(studentId, amount);
        this.cashierName = cashierName;
    }

    @Override
    public String getPaymentMethod() {
        return "Cash";
    }

    @Override
    public String getPaymentDetails() {
        return "Received in cash by cashier: " + cashierName;
    }
}
