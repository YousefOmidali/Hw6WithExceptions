import java.math.BigInteger;

public class Card {
    Integer id;
    Integer cvv2;
    Integer password;
    Long cardNumber;


    public Card() {
    }

    public Card(Integer id, Integer cvv2, Integer password, Long cardNumber) {
        this.id = id;
        this.cvv2 = cvv2;
        this.password = password;
        this.cardNumber = cardNumber;
    }

    public Card(Integer cvv2, Integer password, Long cardNumber) {
        this.cvv2 = cvv2;
        this.password = password;
        this.cardNumber = cardNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCvv2() {
        return cvv2;
    }

    public void setCvv2(Integer cvv2) {
        this.cvv2 = cvv2;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

}
