import java.sql.*;
import java.time.LocalDate;

public class TransactionRepository {
    private Connection connection = MyConnection.connection;

    public TransactionRepository() throws SQLException {
        String createTable = "create table if not exists transaction (" +
                "id serial primary key," +
                "card_id Integer  ," +
                "amount int," +
                "date date," +
                "CONSTRAINT fk_transaction_id FOREIGN KEY (card_id) REFERENCES card (Id))";
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

    public TransactionList findById(Integer id, Date startDate, Date endDate) throws SQLException {
        String findById = "select  * from transaction  where id = ? And date between  ? and  ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1, id);
        preparedStatement.setDate(2, startDate);
        preparedStatement.setDate(3, endDate);
        ResultSet resultSet = preparedStatement.executeQuery();
        preparedStatement.close();
        TransactionList transactionList = new TransactionList();
        if (resultSet.next()) {
            transactionList.add(new Transaction(resultSet.getInt("amount"),resultSet.getDate("date"),
                    resultSet.getInt("account_id")));
        }
        return transactionList;
    }

}