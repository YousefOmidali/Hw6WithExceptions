import Exceptions.UnableToChangePassword;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        AccountRepository accountRepository = new AccountRepository();
        CardRepository cardRepository = new CardRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Services services = new Services();
        Random random = new Random();

        String firstName;
        String lastName;
        Integer nationalCode;
        Integer amount;
        int cvv2 = 1000 + random.nextInt(999);
        Integer password;
        Integer firstCardPassword;
        String secondCardPassword;
        //Long cardNumber = (random.nextLong() % 10000000000000000L) + (5000000000000000L);
        Long cardNumber = (random.nextLong() % 100000000L) + (6104337500000000L);
        Integer nationalCodeChangePassword = 0;
        Long firstCardNumber;
        Long secondCardNumber;
        int firstCardCvv2;
        int secondCardCvv2;
        Card card = null;
        Integer order=5;

        while (true) {
            System.out.println("What do you want to do : \n1.Make an account \n2.Money transfer" +
                    " \n3.change password \n4.Transactions \n5.Exit ");

            try {
                order = scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Input a Digit! ");
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
                    password = scanner.nextInt();
                    services.createAccount(firstName, lastName, nationalCode, amount, null, AccountStatus.ALLOW,
                            cvv2, password, cardNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Only enter Digit! ");
                } catch (InputMismatchException a) {
                    System.out.println("Only enter Digit! ");
                } catch (NullPointerException t) {
                    System.out.println("nullPointerException! ");
                }

                System.out.println("Account and Card is made ");
                System.out.println("Your cvv2 is:" + cvv2 + "  and your cardNumber is: " + cardNumber + "\n please take a note of them! ");
            }
            if (order == 2) {
                try {
                    System.out.println("enter your card number : ");
                    firstCardNumber = scanner.nextLong();
                    System.out.println("enter your password: ");
                    firstCardPassword = scanner.nextInt();
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
                } catch (InputMismatchException o) {
                    System.out.println("Invalid Input!");
                }


            }
            if (order == 3) {
                int numberOfWrongPasswordEntered = 0;
                System.out.println("enter your card number : ");
                try {
                    firstCardNumber = scanner.nextLong();
                    System.out.println("enter your national code: ");
                    nationalCodeChangePassword = scanner.nextInt();
                    System.out.println("enter your current password");
                    password = scanner.nextInt();
                    card = new Card(null, password, firstCardNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Only enter Digit! ");
                } catch (InputMismatchException o) {
                    System.out.println("Invalid Input!");
                }

                while (numberOfWrongPasswordEntered < 4) {
                    if (cardRepository.checkPassword(card)) {
                        System.out.println("Enter your new password: ");
                        Integer newPassword = scanner.nextInt();
                        try {
                            if (cardRepository.changePassword(card, newPassword)) {
                                System.out.println("Done! ");
                            } else throw new UnableToChangePassword("Unable to change password ");
                        } catch (UnableToChangePassword e) {
                            e.getMessage();
                        }

                        break;
                    } else {
                        System.out.println("wrong password! ");
                        numberOfWrongPasswordEntered++;
                    }
                    if (numberOfWrongPasswordEntered == 3) {   //Block account after 3 wrong password
                        services.blockAccountByNationalCode(nationalCodeChangePassword);
                    }
                }
            }
            if (order == 4) {
                System.out.println("enter your card number : ");
                firstCardNumber = scanner.nextLong();
                System.out.println("enter your national code: ");
                try {
                    nationalCode = scanner.nextInt();
                    System.out.println("enter your password");
                    password = scanner.nextInt();
                    scanner.nextLine();
                    card = new Card(null, password, firstCardNumber);
                    System.out.println("enter your start date: yyyyMMdd ");
                    Date startDate = Date.valueOf(scanner.nextLine());
                    System.out.println("enter your start date: yyyyMMdd ");
                    Date endDate = Date.valueOf(scanner.nextLine());
                    services.viewTransactions(card, startDate, endDate);
                }catch (NullPointerException e){
                    System.out.println("Null Pointer exception");
                }catch (InputMismatchException a){
                    System.out.println("Just enter Digit! ");
                }catch (NumberFormatException o) {
                    System.out.println("Just enter Digit! ");
                }
            }
            if (order == 5)
                break;
        }
    }
}
