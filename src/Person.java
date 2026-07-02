/**
 * Abstract superclass for every human actor in the system (Student, Admin).
 * Declares the shared, encapsulated state (id, name, contact) and forces
 * subclasses to supply their own definition of getRoleDescription(),
 * which is the hook used later for polymorphic behaviour.
 */
public abstract class Person {

    private String id;
    private String fullName;
    private String contact;

    public Person(String id, String fullName, String contact) {
        this.id = id;
        this.fullName = fullName;
        this.contact = contact;
    }

    // Encapsulation: fields are private, accessed only through these methods
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    /** Abstraction: every concrete Person must describe its own role. */
    public abstract String getRoleDescription();

    @Override
    public String toString() {
        return String.format("%s [%s] - %s", fullName, id, getRoleDescription());
    }
}
