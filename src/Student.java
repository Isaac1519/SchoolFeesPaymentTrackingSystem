/**
 * A student enrolled in the school. Inherits identity fields from Person
 * and adds academic-specific state.
 */
public class Student extends Person {

    private String program;
    private String level; // e.g. Level 100, 200, MSc Year 1
    private final FeeInvoice invoice;

    public Student(String id, String fullName, String contact, String program, String level, double totalFeesDue) {
        super(id, fullName, contact);
        this.program = program;
        this.level = level;
        this.invoice = new FeeInvoice(id, totalFeesDue);
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public FeeInvoice getInvoice() {
        return invoice;
    }

    /** Polymorphism: overrides Person's abstract method with student-specific text. */
    @Override
    public String getRoleDescription() {
        return "Student (" + program + ", " + level + ")";
    }
}
