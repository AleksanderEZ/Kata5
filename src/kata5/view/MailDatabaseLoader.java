package kata5.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class MailDatabaseLoader implements DatabaseLoader {

    @Override
    public Set<String> load(String database) {
        Set<String> result = new TreeSet<>();
        try {
            Class.forName("org.sqlite.JDBC");
            try ( Connection connection = DriverManager.getConnection(database);
                    Statement statement = connection.createStatement()) {
                ResultSet set = statement.executeQuery("SELECT * FROM people");
                while (set.next()) {
                    String email = set.getString("email");
                    result.add(email);
                }
            }
        } catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException at DatabaseMailLoader::load()" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException at DatabaseMailLoader::load()" + e.getMessage());
        }
        return result;
    }
}