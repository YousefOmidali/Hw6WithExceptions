import Exceptions.CouldNotMakeAccountExceptions;
import Exceptions.InvalidCardToCardServiceException;

import java.sql.SQLException;
import java.time.LocalDate;


public class Services {
    private AccountRepository accountRepository = new AccountRepository();
    private CardRepository cardRepository = new CardRepository();
    private TransactionRepository transactionRepository = new TransactionRepository();
    LocalDate date =LocalDate.now();
    Boolean cardToCardStatus = false;

    public Services() throws SQLException {
    }

    public void createAccount(String firstName, String lastName, Integer nationalCode,
                              Integer amount, Branch branch, AccountStatus accountStatus,
                              Integer cvv2, String password, Long cardNumber) throws SQLException {

        Customer customer = new Customer(firstName, lastName, nationalCode);
        Card card = new Card(cvv2, password, cardNumber);
        Account account1 = new Account(customer, amount, branch, accountStatus, card);
        accountRepository.insert(account1);
        cardRepository.insert(card);
    }

    public void CardToCard(Card firstCard, Card secondCard, Integer amount) throws SQLException {
        if (cardRepository.checkDigitsOfCardNumber(firstCard.cardNumber) &&
                cardRepository.checkDigitsOfCardNumber(secondCard.cardNumber)) {//check for number of digits  16 or 12
            if (cardRepository.checkByCardNumber(firstCard.cardNumber) &&
                    cardRepository.checkByCardNumber(secondCard.cardNumber)) {  // check for if card exists
                if (accountRepository.checkAccountStatus(firstCard.cardNumber)) {// if Account not BLOCKED !
                    if (cardRepository.checkPassword(firstCard)) {
                        if (cardRepository.getAmount(firstCard.cardNumber) >= amount) {  // check for if firstCard have enough money or not
                            Integer restAmount = cardRepository.getAmount(firstCard.cardNumber) - amount - 600;
                            Integer additionalAmount = cardRepository.getAmount(secondCard.cardNumber);
                            cardRepository.setAmount(firstCard, restAmount);
                            cardRepository.setAmount(secondCard, additionalAmount + amount);
                            Transaction transaction = new Transaction(cardRepository.getAccount(firstCard.getId()), amount, date);
                            transactionRepository.insert(transaction);
                            cardToCardStatus = true;
                            System.out.println("Done! ");
                        } else System.out.println("Not enough money! ");
                    } else System.out.println("Wrong password! ");
                } else System.out.println("there is no such a card number ");
            } else System.out.println("Your Account is Blocked! ");
        } else System.out.println("Wrong card number (digit) ");

        if (cardToCardStatus = false){
            throw  new InvalidCardToCardServiceException("Failed to Transfer amount!");
        }
    }

    public void blockAccountByNationalCode(Integer nationalCode) throws SQLException {
        accountRepository.blockAccount(accountRepository.findByNationalCode(nationalCode));
    }
    public void CouldNotMakeAccount(){
        throw new CouldNotMakeAccountExceptions("sorry couldn't make account! ");
    }
}
