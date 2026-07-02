/**
 * A staff member (bursar / accounts officer) who is authorised to operate
 * the fees tracking application.
 */
public class Admin extends Person {

    private String designation; // e.g. Bursar, Accounts Clerk

    public Admin(String id, String fullName, String contact, String designation) {
        super(id, fullName, contact);
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /** Polymorphism: a different override of the same abstract method. */
    @Override
    public String getRoleDescription() {
        return "Admin (" + designation + ")";
    }
}
