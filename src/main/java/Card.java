import Exceptions.InvalidCvv2Exception;

import java.math.BigInteger;

public class Card {
    Integer id;
    Integer cvv2;
    String password;
    Long cardNumber;


    public Card() {
    }

    public Card(Integer id, Integer cvv2, String password, Long cardNumber) {
        this.id = id;
        this.cvv2 = cvv2;
        this.password = password;
        this.cardNumber = cardNumber;
    }

    public Card(Integer cvv2, String password, Long cardNumber) {
        InvalidCvv2(cvv2);
        this.cvv2 = cvv2;
        this.password = password;
        this.cardNumber = cardNumber;
    }

    public Card(Account byNationalCode) {

    }

    public Card(String password, Long firstCardNumber) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cvv2=" + cvv2 +
                ", password='" + password + '\'' +
                ", cardNumber=" + cardNumber +
                '}';
    }

    public void InvalidCvv2(Integer cvv2) {
        int temp = cvv2;
        int digitNumber = 0;
        while (temp > 0) {
            temp /= 10;
            digitNumber++;
        }
        if (digitNumber > 4) {
            throw new InvalidCvv2Exception("Invalid cvv2! ");
        }
    }
}
