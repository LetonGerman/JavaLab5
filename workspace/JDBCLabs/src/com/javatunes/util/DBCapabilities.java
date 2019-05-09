package com.javatunes.util;

import java.io.File;
import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

class DBCapabilities {

  public static void main(String[] args) {
    Connection conn = null;
    try {
      Properties dbProps = new Properties();
      File myFile = new File("word.txt");
      System.out.println("Attempting to read from file in: " + myFile.getCanonicalPath());
      dbProps.load(new FileInputStream("jdbc_build.properties"));

      String driver = dbProps.getProperty("db.driver");
      String url = dbProps.getProperty("db.url");

      Class.forName(driver);

      String user = dbProps.getProperty("db.user");
      if (user != null) {
        String passwd = dbProps.getProperty("db.passwd");
        conn = DriverManager.getConnection(url, user, passwd);
      } else {
        conn = DriverManager.getConnection(url);
      }

      DatabaseMetaData dbmd = conn.getMetaData();

      System.out.println();
      System.out.println("Connected to:     " + dbmd.getURL());
      System.out.println("Connected as:     " + dbmd.getUserName());
      System.out.println("Driver name:      " + dbmd.getDriverName());
      System.out.println("Driver version:   " + dbmd.getDriverVersion());
      System.out.println("Database name:    " + dbmd.getDatabaseProductName());
      System.out.println("Database version: " + dbmd.getDatabaseProductVersion());
      System.out.println();

      boolean scrollInsensitiveReadOnly = dbmd.supportsResultSetConcurrency(
          ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

      boolean scrollInsensitiveUpdatable = dbmd.supportsResultSetConcurrency(
          ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

      boolean scrollSensitiveReadOnly = dbmd.supportsResultSetConcurrency(
          ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

      boolean scrollSensitiveUpdatable = dbmd.supportsResultSetConcurrency(
          ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

      boolean batchUpdates = dbmd.supportsBatchUpdates();

      System.out.println("true means the feature is supported, false means it is not.");
      System.out.println("-----------------------------------------------------------");

      System.out
          .println("Scrollable, insensitive, read-only ResultSets: " + scrollInsensitiveReadOnly);
      System.out
          .println("Scrollable, insensitive, updatable ResultSets: " + scrollInsensitiveUpdatable);
      System.out.println();

      System.out
          .println("Scrollable, sensitive, read-only ResultSets:   " + scrollSensitiveReadOnly);
      System.out
          .println("Scrollable, sensitive, updatable ResultSets:   " + scrollSensitiveUpdatable);
      System.out.println();

      System.out.println("Batch updates:                                 " + batchUpdates);
      System.out.println();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
          System.out.println("Connection closed.");
        }
      } catch (SQLException ignored) {
      }
    }
  }
}
