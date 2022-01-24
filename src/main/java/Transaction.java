import java.sql.Date;
import java.time.LocalDate;

public class Transaction {
    private Integer id;
    private Account account;
    private Integer amount;
    private Date date;


    public Transaction() {
    }

    public Transaction(Account account, Integer amount, Date date) {
        this.account = account;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(Integer id, Account account, Integer amount, Date date) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(int amount, String account_id, Date date) {

    }

    public Transaction(int amount, Date date, int account_id) {
    }

    public Transaction(Account account, Integer amount, LocalDate date) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", account=" + account +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
