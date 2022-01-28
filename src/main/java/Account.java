public class Account {
    private Customer customer;
    private Integer amount;
    private Integer id;
    private Branch branch;
    private AccountStatus accountStatus;
    private Card card;

    public Account() {
    }

    public Account(int amount, int id, String branch) {
    }

    public Account(Customer customer, Integer amount,  Branch branch, AccountStatus accountStatus, Card card) {
        this.customer = customer;
        this.amount = amount;
        this.branch = branch;
        this.accountStatus = accountStatus;
        this.card = card;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account(Customer customer, Integer amount, Integer id, Branch branch, AccountStatus accountStatus, Card card) {
        this.customer = customer;
        this.amount = amount;
        this.id = id;
        this.branch = branch;
        this.accountStatus = accountStatus;
        this.card = card;
    }

    public Account(Integer amount, Integer id, Branch branch, AccountStatus accountStatus) {
        this.amount = amount;
        this.id = id;
        this.branch = branch;
        this.accountStatus = accountStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Account{" +
                "customer=" + customer +
                ", amount=" + amount +
                ", branch=" + branch +
                ", accountStatus=" + accountStatus +
                ", card=" + card +
                '}';
    }
}
