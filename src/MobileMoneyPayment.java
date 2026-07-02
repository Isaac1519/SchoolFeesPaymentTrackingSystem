/** Payment made via a mobile money network (MTN MoMo, Telecel Cash, AirtelTigo Money). */
public class MobileMoneyPayment extends Payment {

    private final String network;
    private final String momoNumber;
    private final String transactionId;

    public MobileMoneyPayment(String studentId, double amount, String network, String momoNumber, String transactionId) {
        super(studentId, amount);
        this.network = network;
        this.momoNumber = momoNumber;
        this.transactionId = transactionId;
    }

    @Override
    public String getPaymentMethod() {
        return "Mobile Money";
    }

    @Override
    public String getPaymentDetails() {
        return String.format("%s | Number: %s | Txn ID: %s", network, momoNumber, transactionId);
    }
}
