import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Person {
    private int occupation;
    private String forename;
    private String surname;
    private DateOfBirth dateOfBirth;
    private String password;
    private String iD;
    private String userName;

    public Person(int occupation, String forename, String surname, DateOfBirth dateOfBirth, String password) {
        this.occupation = occupation;
        this.forename = forename;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.userName = forename.indexOf(0)+ surname;
    }

    public Person(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUniqueUsername() {
        return forename.charAt(0)+surname;
    }

    public String getUniqueId() {
        return iD;
    }

    public String getPassword() {
        return password;
    }

    public void setOccupation(int occupation) {
        if (occupation < 2 && occupation >= 0) {
            this.occupation = occupation;
        }
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void createUniqueID() {
        Connection connection = new DBConnection().getConn();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select last_insert_ID();");
            while (resultSet.next()) {
                iD = occupation + resultSet.getString("last_insert_ID()");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return occupation +", \""+forename+"\", \""+ surname +"\", "+dateOfBirth;
    }
}
