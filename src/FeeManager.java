import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Central service class that owns the in-memory "database" of the system:
 * a map of students keyed by ID and a running list of all payments made.
 * All GUI panels talk to a single shared FeeManager instance.
 */
public class FeeManager {

    private final Map<String, Student> students = new LinkedHashMap<>();
    private final List<Payment> payments = new ArrayList<>();

    public void registerStudent(Student student) throws DuplicateStudentException {
        if (students.containsKey(student.getId())) {
            throw new DuplicateStudentException("A student with ID " + student.getId() + " already exists.");
        }
        students.put(student.getId(), student);
    }

    public Student findStudent(String studentId) throws StudentNotFoundException {
        Student s = students.get(studentId);
        if (s == null) {
            throw new StudentNotFoundException("No student found with ID " + studentId);
        }
        return s;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    /**
     * Records a payment against a student's invoice and stores it in the
     * transaction history. This is the single method every payment-entry
     * panel (cash, mobile money, bank transfer) funnels through.
     */
    public void recordPayment(Payment payment) throws StudentNotFoundException, InvalidAmountException, OverpaymentException {
        Student student = findStudent(payment.getStudentId());
        student.getInvoice().applyPayment(payment.getAmount());
        payments.add(payment);
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    public List<Payment> getPaymentsForStudent(String studentId) {
        List<Payment> result = new ArrayList<>();
        for (Payment p : payments) {
            if (p.getStudentId().equals(studentId)) {
                result.add(p);
            }
        }
        return result;
    }

    public double getTotalCollected() {
        double total = 0;
        for (Payment p : payments) {
            total += p.getAmount();
        }
        return total;
    }

    public double getTotalOutstanding() {
        double total = 0;
        for (Student s : students.values()) {
            total += s.getInvoice().getBalance();
        }
        return total;
    }

    public int countDefaulters() {
        int count = 0;
        for (Student s : students.values()) {
            if (!s.getInvoice().isFullyPaid()) {
                count++;
            }
        }
        return count;
    }

    /** Loads a small set of sample records so the GUI is not empty on first run. */
    public void loadSampleData() {
        try {
            registerStudent(new Student("SJS001", "Sistus Arthur", "0276251519", "General Arts", "Year 2", 500.00));
            registerStudent(new Student("SJS002", "Bishop Nii Nelson", "02546809305", "Business", "Year 2", 800.00));
            registerStudent(new Student("SJS003", "Wisdom Kpobi", "0536305569", "Visual Art", "Year 3", 600.00));
            recordPayment(new CashPayment("SJS001", 500.00, "J. Antwi"));
            recordPayment(new MobileMoneyPayment("SJS002", 200.00, "MTN MoMo", "0244000002", "MP240001"));
        } catch (DuplicateStudentException | StudentNotFoundException | InvalidAmountException | OverpaymentException e) {
            // Safe to ignore for sample/demo data seeded at startup.
            System.err.println("Could not load sample data: " + e.getMessage());
        }
    }
}
