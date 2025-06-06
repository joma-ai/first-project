import java.sql.*;

public class DBConnection {
    String uRL;
    String username;
    String password;
    Connection connection;

    public DBConnection() {
        uRL = "jdbc:mysql://127.0.0.1:3306/appidb";
        username = "root";
        password = "skuellisalasona";

        try {
            connection = DriverManager.getConnection(uRL, username, password);


        }   catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return connection;
    }

    public void addPerson(Person person) {
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into persons (occupation, first_name, last_name, date_of_birth) values ("+person.toString()+");");
            int addedRows = preparedStatement.executeUpdate();


        } catch (Exception error) {
            error.printStackTrace();
        }
    }

}
