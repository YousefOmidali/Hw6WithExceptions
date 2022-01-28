import java.sql.*;
import java.time.LocalDate;

public class TransactionRepository {
    private Connection connection = MyConnection.connection;

    public TransactionRepository() throws SQLException {
        String createTable = "create table if not exists transaction (" +
                "id serial primary key," +
                "account_id Integer  ," +
                "amount int," +
                "date date," +
                "CONSTRAINT fk_transaction_id FOREIGN KEY (account_id) REFERENCES Account (Id));";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void insert(Transaction transaction) throws SQLException {
        String insert = "insert into transaction (amount, account_id , date ) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, transaction.getAmount());
        preparedStatement.setInt(2, transaction.getAccount().getId());
        preparedStatement.setDate(3, transaction.getDate());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void update(Transaction transaction) throws SQLException {
        String update = "update transaction set amount = ? , account_id = ? , date = ? where id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setInt(1, transaction.getAmount());
        preparedStatement.setInt(2, transaction.getAccount().getId());
        preparedStatement.setDate(3, transaction.getDate());
        preparedStatement.setInt(4, transaction.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void delete(Integer id) throws SQLException {
        String delete = "delete from transaction where id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public TransactionList findByCardNumber(Card card , Date startDate, Date endDate) throws SQLException {
        String findById = "select * from transaction " +
                "inner join account a on a.id = transaction.account_id " +
                "inner join card c on a.id = c.account_id where" +
                " card_number = ? and date between ? and ? ;\n";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setLong(1, card.getCardNumber());
        preparedStatement.setDate(2, startDate);
        preparedStatement.setDate(3, endDate);
        ResultSet resultSet = preparedStatement.executeQuery();

        TransactionList transactionList = new TransactionList();
        if (resultSet.next()) {
            transactionList.add(new Transaction(resultSet.getInt("amount"), resultSet.getDate("date"),
                    resultSet.getInt("account_id")));
        }
        preparedStatement.close();
        return transactionList;
    }

}