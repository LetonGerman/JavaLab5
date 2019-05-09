/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2004-8 LearningPatterns Inc.
 */

package com.javatunes.util;

import java.sql.*;

public class JDBCUtilities {

  public static void printDatabaseMetaData(DatabaseMetaData dbdata) {
    try {
      System.out.println();
      System.out.println("Connected to:     " + dbdata.getURL());
      System.out.println("Connected as:     " + dbdata.getUserName());
      System.out.println("Driver name:      " + dbdata.getDriverName());
      System.out.println("Driver version:   " + dbdata.getDriverVersion());
      System.out.println("Database name:    " + dbdata.getDatabaseProductName());
      System.out.println("Database version: " + dbdata.getDatabaseProductVersion());
      System.out.println();
    } catch (SQLException sqle) {
      JDBCUtilities.printSQLException(sqle);
    }
  }

  public static boolean checkForWarnings(SQLWarning warning) {
    boolean rc = false;
    if (warning != null) {
      System.out.println("\n*** Warning ***\n");
      rc = true;
      while (warning != null) {
        System.out.println("Message:     " + warning.getMessage());
        System.out.println("SQLState:    " + warning.getSQLState());
        System.out.println("Vendor code: " + warning.getErrorCode());
        System.out.println();
        warning = warning.getNextWarning();
      }
    }
    return rc;
  }

  private static void printSQLException(SQLException sqle) {
    sqle.printStackTrace();
    System.out.println();
    while (sqle != null) {
      System.out.println("Message:     " + sqle.getMessage());
      System.out.println("SQLState:    " + sqle.getSQLState());
      System.out.println("Vendor code: " + sqle.getErrorCode());
      System.out.println();
      sqle = sqle.getNextException();
    }
  }

  public static void printResultSet(ResultSet rs) throws SQLException {
    int i;
    ResultSetMetaData rsmd = rs.getMetaData();
    int numCols = rsmd.getColumnCount();
    for (i = 1; i <= numCols; i++) {
      if (i > 1) {
        System.out.print(",");
      }
      System.out.print(rsmd.getColumnLabel(i));
    }
    System.out.println();
    while (rs.next()) {
      for (i = 1; i <= numCols; i++) {
        if (i > 1) {
          System.out.print(",");
        }
        System.out.print(rs.getString(i));
      }
      System.out.println();
    }
  }
}
