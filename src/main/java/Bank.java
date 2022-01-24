import Exceptions.CouldNotChangePasswordException;
import Exceptions.CouldNotMakeAccountExceptions;
import Exceptions.InvalidCardToCardServiceException;
import Exceptions.InvalidCvv2Exception;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class Bank {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        AccountRepository accountRepository = new AccountRepository();
        CardRepository cardRepository = new CardRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Integer nationalCodeChangePassword = null;
        Services services = new Services();
        Random random = new Random();

        String firstName;
        String lastName;
        Integer nationalCode;
        Integer amount;
        int cvv2 = 1000 + random.nextInt(9999);
        String password;
        String firstCardPassword;
        String secondCardPassword;
        Long cardNumber = (random.nextLong() % 1000000000000000L);
        Long firstCardNumber = null;
        Long secondCardNumber;
        int firstCardCvv2;
        int secondCardCvv2;
        Integer order = null;

        System.out.println("What do you want to do : \n1.Make an account \n2.Money transfer \n3.change password ");
        try {
            order = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("invalid input!  must enter number ");
        } catch (NumberFormatException e) {
            System.out.println("invalid format input! ");
        }
        scanner.nextLine();
        if (order == 1) {
            try {
                System.out.println("enter your first name: ");
                firstName = scanner.nextLine();
                System.out.println("enter your last name: ");
                lastName = scanner.nextLine();
                System.out.println("enter your national code: ");
                nationalCode = scanner.nextInt();
                System.out.println("how much money you want to put in account ? ");
                amount = scanner.nextInt();
                System.out.println("enter your password: ");
                password = scanner.nextLine();
                services.createAccount(firstName, lastName, nationalCode, amount, null, AccountStatus.ALLOW,
                        cvv2, password, cardNumber);
            } catch (NumberFormatException e) {
                System.out.println("Only enter Digit! ");
            } catch (InputMismatchException a) {
                System.out.println("Only enter Digit! ");
            } catch (CouldNotMakeAccountExceptions o) {
                o.printStackTrace();
            }

            System.out.println("Account and Card is made ");
        }
        if (order == 2) {
            try {
                System.out.println("enter your card number : ");
                firstCardNumber = scanner.nextLong();
                System.out.println("enter your password: ");
                firstCardPassword = scanner.nextLine();
                System.out.println("enter your cvv2: ");
                firstCardCvv2 = scanner.nextInt();
                Card firstCard = new Card(firstCardCvv2, firstCardPassword, firstCardNumber);
                System.out.println("enter amount: ");
                amount = scanner.nextInt();
                System.out.println("enter destination card number : ");
                secondCardNumber = scanner.nextLong();
                Card secondCard = new Card(null, null, secondCardNumber);
                services.CardToCard(firstCard, secondCard, amount);
            } catch (NumberFormatException e) {
                System.out.println("Only enter Digit! ");
            } catch (InvalidCvv2Exception a) {
                a.printStackTrace();
            } catch (InputMismatchException o) {
                System.out.println("Invalid Input!");
            } catch (InvalidCardToCardServiceException n) {
                n.printStackTrace();
            }


        }
        if (order == 3) {
            int numberOfWrongPasswordEntered = 0;
            System.out.println("enter your card number : ");
            try {
                firstCardNumber = scanner.nextLong();
                System.out.println("enter your national code: ");
                nationalCodeChangePassword = scanner.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Only enter Digit! ");
            } catch (InputMismatchException o) {
                System.out.println("Invalid Input!");
            }


            while (numberOfWrongPasswordEntered < 4) {
                System.out.println("enter your current password");
                password = scanner.nextLine();
                Card card = new Card(password, firstCardNumber);
                if (cardRepository.checkPassword(card)) {
                    System.out.println("Enter your new password: ");
                    String newPassword = scanner.nextLine();
                    try {
                        cardRepository.changePassword(card, newPassword);
                    } catch (CouldNotChangePasswordException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("wrong password! ");
                    numberOfWrongPasswordEntered++;
                }
                if (numberOfWrongPasswordEntered == 3) {   //Block account after 3 wrong password
                    try {
                        services.blockAccountByNationalCode(nationalCodeChangePassword);
                    }catch (CancellationException e){e.printStackTrace();}

                }
            }
        }
    }
}
