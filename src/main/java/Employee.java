public class Employee {
   private String firstName;
   private String lastName;
   private Integer NationalCode;
   private Branch branch;
   private Bosses boss;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Integer nationalCode, Branch branch, Bosses boss) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.NationalCode = nationalCode;
        this.branch = branch;
        this.boss = boss;
    }

    public Employee(String first_name, String last_name, String branch, String boss) {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNationalCode() {
        return NationalCode;
    }

    public void setNationalCode(Integer nationalCode) {
        NationalCode = nationalCode;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Bosses getBoss() {
        return boss;
    }

    public void setBoss(Bosses boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", NationalCode=" + NationalCode +
                ", branch='" + branch + '\'' +
                ", boss='" + boss + '\'' +
                '}';
    }
}
