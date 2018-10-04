package StudentsService.connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static ConnectionManager connectionManager;
    public static ConnectionManager getInstance(){
      if (connectionManager == null){
          connectionManager = new ConnectionManagerJdbcImpl();
      }
      return connectionManager;
    }

    private ConnectionManagerJdbcImpl() {
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/MyTestDB",
                    "postgres",
                    "dcclav");

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  connection;
    }
}
