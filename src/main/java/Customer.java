public class Customer {
   private String firstName;
   private String lastName;
   private Integer NationalCode;

    public Customer(String firstName, String lastName, Integer nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        NationalCode = nationalCode;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", NationalCode=" + NationalCode +
                '}';
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
}
