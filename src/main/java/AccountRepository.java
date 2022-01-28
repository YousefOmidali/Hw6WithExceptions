import java.sql.*;

public class AccountRepository {
    private Connection connection = MyConnection.connection;

    public AccountRepository() throws SQLException {
        String createTable = "create table if not exists Account (\n" +
                "first_name varchar (200),\n" +
                "last_name varchar (200),\n" +
                "national_code int ,\n" +
                "amount int ,\n" +
                "Id serial primary key ,\n" +
                "branch varchar (250),\n" +
                "status varchar (250)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void insert(Account account) throws SQLException {
        String insert = "insert into account (first_name, last_name, national_code,amount, status) values (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, account.getCustomer().getFirstName());
        preparedStatement.setString(2, account.getCustomer().getLastName());
        preparedStatement.setInt(3, account.getCustomer().getNationalCode());
        preparedStatement.setInt(4, account.getAmount());
        preparedStatement.setString(5, String.valueOf(AccountStatus.ALLOW));
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void update(Account account) throws SQLException {
        String update = "update Account set first_name = ?,last_name = ?, national_code = ?, amount = ?, branch = ? , status = ?" +
                "where Id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, account.getCustomer().getFirstName());
        preparedStatement.setString(2, account.getCustomer().getLastName());
        preparedStatement.setInt(3, account.getCustomer().getNationalCode());
        preparedStatement.setInt(4, account.getAmount());
        preparedStatement.setString(5, String.valueOf(account.getBranch()));
        preparedStatement.setString(6, String.valueOf(account.getAccountStatus()));

        preparedStatement.execute();
        preparedStatement.close();

    }

    public void delete(Integer id) throws SQLException {
        String delete = "delete from Account where Id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public Account findByNationalCode(Integer nationalCode) throws SQLException {
        String findByNationalCode = "SELECT * FROM Account " +
                "WHERE national_code = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findByNationalCode);
        preparedStatement.setInt(1, nationalCode);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = null;
        if (resultSet.next()) {
            account = new Account(resultSet.getInt("amount"), resultSet.getInt("Id"), resultSet.getString("branch"));
        }
        preparedStatement.close();
        return account;
    }

    public Integer getAmount(Integer id) throws SQLException {
        String getAmount = "select amount from Account where Id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(getAmount);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Integer amount = 0;
        if (resultSet.next()) {
            amount = resultSet.getInt("amount");
        }preparedStatement.close();
        return amount;

    }

    public Boolean checkAccountStatus(Long cardNumber) throws SQLException {
        Boolean status = false;
        String check = "select *  from Account inner join card c on Account.Id = c.account_id" +
                " where c.card_number = ? and account.status = 'ALLOW' ;";
        PreparedStatement preparedStatement = connection.prepareStatement(check);
        preparedStatement.setLong(1, cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        String statusString;
        if (resultSet.next()) {
           // statusString = resultSet.getString("status");
           // if (statusString.toUpperCase().equals("ALLOW")) {
                status = true;
           // }
        }
        preparedStatement.close();
        return status;
    }

    public void blockAccount(Account account) throws SQLException {
        String blockAccount = "update Account set status = ? where Id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(blockAccount);
        preparedStatement.setString(1, String.valueOf(AccountStatus.BLOCKED));
        preparedStatement.setInt(2,account.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }
}

