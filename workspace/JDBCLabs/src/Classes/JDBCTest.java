package Classes;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTest {

  public static void main(String[] args) {
    Connection conn = null;

    try {

      Class<?> clientDriver = Class.forName("org.apache.derby.jdbc.ClientDriver");
      Properties properties = new Properties();
      properties.put("user", "GUEST");
      conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JavaTunesDB", properties);

      DatabaseMetaData dbmd = conn.getMetaData();

      System.out.println(
          "Metadata: " + dbmd.getDriverName() + ", Username of connection: " + dbmd.getUserName());


    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
