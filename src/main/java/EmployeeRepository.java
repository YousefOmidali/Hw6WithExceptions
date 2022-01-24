import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository {
    private Connection connection = MyConnection.connection;

    public EmployeeRepository() throws SQLException {
        String createTable = "create table if not exists Account (\n" +
                "first_name varchar (200), " +
                "last_name varchar (200), " +
                "national_code int primary key , " +
                "branch varchar (250), " +
                "boss varchar (250)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void insert(Employee employee) throws SQLException {
        String insert = "insert into employee (first_name, last_name, national_code , branch , boss) values (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setInt(3, employee.getNationalCode());
        preparedStatement.setString(4, String.valueOf(employee.getBranch()));
        preparedStatement.setString(5, String.valueOf(employee.getBoss()));
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void delete(Integer nationalCode) throws SQLException {
        String delete = "delete from employee where national_code = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, nationalCode);
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void update(Integer nationalCode, Employee employee) throws SQLException {
        String update = "update employee set first_name = ?,last_name = ?, boss = ? , branch = ? where national_code = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, String.valueOf(employee.getBoss()));
        preparedStatement.setString(4, String.valueOf(employee.getBranch()));
        preparedStatement.setInt(5, nationalCode);
        preparedStatement.execute();
        preparedStatement.close();

    }

    public Employee findByNationalCode(Integer nationalCode) throws SQLException {
        String findByNationalCode = "SELECT * FROM employee " +
                "WHERE national_code = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findByNationalCode);
        preparedStatement.setInt(1, nationalCode);
        ResultSet resultSet = preparedStatement.executeQuery();
        Employee employee = null;
        if (resultSet.next()) {
            employee = new Employee(resultSet.getString("first_name"),
                    resultSet.getString("last_name"), resultSet.getString("branch"),
                    resultSet.getString("boss"));
        }
        return employee;
    }

}
