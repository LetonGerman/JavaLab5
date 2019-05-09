package com.javatunes.util;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ItemDAO {

  private Connection m_conn = null;

  public ItemDAO() {

  }

  public ItemDAO(Connection conn) throws SQLException {
    m_conn = conn;
  }

  public MusicItem searchById(Long id) throws SQLException {
    String sql = "SELECT * FROM GUEST.ITEM WHERE ITEM_ID=" + id;
    MusicItem result = null;
    Statement stmt = null;
    try {
      Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JavaTunesDB");
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      rs.next();

      result = new MusicItem(rs.getLong("ITEM_ID"), rs.getString("TITLE"), rs.getString("ARTIST"),
          rs.getDate("RELEASEDATE"), rs.getBigDecimal("LISTPRICE"), rs.getBigDecimal("PRICE"));
    } catch (Exception e) {
      return null;
    } finally {
      stmt.close();
    }
    return result;
  }

  public Collection<MusicItem> searchByKeyword(String keyword) throws SQLException {
    String wildcarded = "%" + keyword + "%";

    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JavaTunesDB");
    Statement stmt = conn.createStatement();
    ArrayList<MusicItem> list = new ArrayList();

    ResultSet rs = stmt
        .executeQuery("SELECT * FROM GUEST.ITEM WHERE TITLE LIKE \'" + wildcarded + "\'");
    while (rs.next()) {
      MusicItem res = new MusicItem(rs.getLong("ITEM_ID"), rs.getString("TITLE"),
          rs.getString("ARTIST"),
          rs.getDate("RELEASEDATE"), rs.getBigDecimal("LISTPRICE"), rs.getBigDecimal("PRICE"));
      list.add(res);
    }
    return list;
  }

  public void create(MusicItem item)
      throws SQLException {
    java.sql.Date releaseDate = new java.sql.Date(item.getReleaseDate().getTime());

    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JavaTunesDB");
    Statement stmt = conn.createStatement();
    String sql =
        "INSERT INTO GUEST.ITEM(TITLE,ARTIST,RELEASEDATE,LISTPRICE,PRICE,VERSION)" + " VALUES"
            + "(?,?,?,?,?,?)";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    preparedStatement.setString(1, item.getTitle());
    preparedStatement.setString(2, item.getArtist());
    preparedStatement.setDate(3, releaseDate);
    preparedStatement.setBigDecimal(4, item.getListPrice());
    preparedStatement.setBigDecimal(5, item.getPrice());
    preparedStatement.setInt(6, 1);

    System.out.println("Updated: " + preparedStatement.executeUpdate());
    conn.commit();
  }

  public void close() {

  }
}
