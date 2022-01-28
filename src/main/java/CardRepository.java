import Exceptions.UnableToChangePassword;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardRepository {
    private Connection connection = MyConnection.connection;

    public CardRepository() throws SQLException {
        String createTable = "create table if not exists card (\n" +
                "id serial primary key ,\n" +
                "account_id INTEGER,\n" +
                "card_number bigint,\n" +
                "cvv2 integer,\n" +
                "password INTEGER ,\n" +
                "CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES Account (Id));";

        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void insert(Card card) throws SQLException {
        String insert = "insert into card(card_number, cvv2 , password ) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setLong(1, card.getCardNumber());
        preparedStatement.setInt(2, card.getCvv2());
        preparedStatement.setInt(3, card.getPassword());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void update(Card card, Long cardNumber) throws SQLException {
        String update = "update card set card_number = ? , cvv2 = ? , password = ? where card_number = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setLong(1, card.getCardNumber());
        preparedStatement.setInt(2, card.getCvv2());
        preparedStatement.setInt(3, card.getPassword());
        preparedStatement.setLong(4, cardNumber);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void delete(Long cardNumber) throws SQLException {
        String delete = "delete from card where card_number = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1, cardNumber);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public Boolean checkByCardNumber(Long cardNumber) throws SQLException {
        Boolean status = false;
        String exists = "select * from card where card_number = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(exists);
        preparedStatement.setLong(1, cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            status = true;
        preparedStatement.close();
        return status;
    }

    public Integer getAmount(Long cardNumber) throws SQLException {
        String getAmount = "select amount from Account inner join card c on Account.Id = c.account_id where card_number = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(getAmount);
        preparedStatement.setLong(1, cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        Integer amount = 0;
        if (resultSet.next()) {
            amount = Integer.valueOf(resultSet.getString("amount"));
        }
        preparedStatement.close();
        return amount;
    }

    public void setAmount(Card card, Integer amount) throws SQLException {
        String setAmount = "update Account set amount = ? where Id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(setAmount);
        preparedStatement.setInt(1, amount);
        preparedStatement.setInt(2, card.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public Boolean checkDigitsOfCardNumber(Long cardNumber) throws SQLException {
        Boolean status = false;
        int digit = 0;
        while (cardNumber > 0) {
            cardNumber /= 10;
            digit++;
            if (digit == 16 || digit == 12)
                status = true;
        }
        return status;
    }

    public Account getAccount(Integer cardNumber) throws SQLException {
        String getAccount = "select * from Account inner join card c on Account.Id = c.id where c.card_number = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(getAccount);
        preparedStatement.setInt(1, cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = new Account();
        if (resultSet.next()) {
            account = new Account(resultSet.getInt("amount"), resultSet.getInt("Id"),
                    resultSet.getString(account.getAccountStatus().name()));
        }
        return account;

    }

    public Boolean changePassword(Card card, Integer password) throws SQLException {
        Boolean status = false;
        String changePassword = "update card set password = ? where card_number = ? ;\n";
        PreparedStatement preparedStatement = connection.prepareStatement(changePassword);
        preparedStatement.setInt(1, password);
        preparedStatement.setLong(2, card.getCardNumber());
        if (preparedStatement.execute()) {
            status = true; }
        preparedStatement.close();
        return status;
    }

    public Boolean checkPassword(Card card) throws SQLException {
        Boolean passwordStatus = false;
        String checkPassword = "select * from card where card_number = ? and password = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(checkPassword);
        preparedStatement.setLong(1, card.getCardNumber());
        preparedStatement.setInt(2, card.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            passwordStatus = true;
        preparedStatement.close();
        return passwordStatus;
    }
}
